/*
 * Activiti Modeler component part of the Activiti project
 * Copyright 2005-2014 Alfresco Software, Ltd. All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

/*
 * Condition expression
 */

var KisBpmConditionExpressionCtrl = [ '$scope', '$modal', function($scope, $modal) {

    // Config for the modal window
    var opts = {
        template:  'editor-app/configuration/properties/condition-expression-popup.html?version=' + Date.now(),
        scope: $scope
    };

    // Open the dialog
    $modal(opts);
}];

var KisBpmConditionExpressionPopupCtrl = [ '$scope', '$translate', '$http', function($scope, $translate, $http) {

    /*---------------------流程设计器扩展可选条件--------------------*/
    
    //参数初始化
    $scope.conditionGridData = [];//表格数据
    $scope.conditionGridDataName = 'conditionGridData';
    $scope.selectConditionTitle = '选择条件';
    //表格列数据
    $scope.conditionColumnData = [ 
        {
        	field : 'paramCode',
        	displayName : '字段编码',
        	minWidth : 100,
        	width : '25%'
        }, 
        {
        	field : 'paramName',
        	displayName : '字段名称',
        	minWidth : 100,
        	width : '30%'
        },
        {  
            field : 'subTable',
            type:'number',  
            displayName : '字段类型',  
            minWidth: 100,  
            width : '15%',
            cellTemplate : '<span>{{!row.entity.subTable?"主表":"明细"}}</span>'
        }, 
        {
        	field : 'remarks',
        	displayName : '备注',
        	minWidth : 100,
        	width : '30%'
        }
	];
    $scope.conditionColumnDataName = 'conditionColumnData';
    $scope.conditionTotalServerItems = 0;// 表格总条数
    var a = window.location.search.substring(1).split("&")[2];
    $scope.orgUnitNo = a.substring(10);// 组织
    var b = window.location.search.substring(1).split("&")[3];
    $scope.billTypeId = b.substring(11);// 单据Id
    // 分页初始化
    $scope.conditionPagingOptions = {
        pageSizes: [10, 20, 30],// page 行数可选值
        pageSize: 10, // 每页行数
        currentPage: 1, // 当前显示页数
    };
    
    //转换map
    var conditionMap = new Map();
    var compareMap = new Map();
    compareMap.set("!=", "activityCondition.valueNe");
    compareMap.set("~", "activityCondition.valueLike");
    compareMap.set(">=", "activityCondition.valueGe");
    compareMap.set(">", "activityCondition.valueGt");
    compareMap.set("<=", "activityCondition.valueLe");
    compareMap.set("<", "activityCondition.valueLt");
    compareMap.set("=", "activityCondition.valueEq");
    
    $http({
        method: 'POST',
        url: ACTIVITI.CONFIG.contextRoot+'/model/getConditionListNoPage',
        params:{
        	'billTypeId': $scope.billTypeId,
        	'orgUnitNo': $scope.orgUnitNo
        },
    }).then(function successCallback(response) {
    		var rows = response.data.rows;
    		if(rows.length<$scope.conditionPagingOptions.pageSize){
				$scope.conditionGridData = rows;
			}else{
				$scope.conditionGridData = rows.slice(0,$scope.conditionPagingOptions.pageSize);
			}
    		for(var i=0;i<rows.length;i++){
    			if(!rows[i].subTable){
    				conditionMap.set(rows[i].paramCode, rows[i].paramName);
    			}else{
    				conditionMap.set(rows[i].paramCode+'List', rows[i].paramName);
    			}
			}
    		if(($scope.conditionExpressionName == '' || $scope.conditionExpressionName == undefined) && ($scope.property.value !== undefined && $scope.property.value !== null)){
    			$scope.conditionExpressionName = '';
    			$scope.getNameByValue($scope.property.value);
    		}
            $scope.conditionTotalServerItems = response.data.total;
        }, function errorCallback(response) {
            // 请求失败执行代码
            $scope.conditionGridData = [];
            $scope.conditionTotalServerItems = 0;
    });
    //分页数据监视
    $scope.$watch('conditionPagingOptions', function (newValue, oldValue) {
        $scope.getConditionPagedDataAsync($scope.conditionPagingOptions.currentPage, $scope.conditionPagingOptions.pageSize);    
    },true); 
    
    //异步请求表格数据
    $scope.getConditionPagedDataAsync = function(pageNum, pageSize){      
        var url = '/model/getConditionList';;
        $http({
            method: 'POST',
            url: ACTIVITI.CONFIG.contextRoot+url,
            params:{
            	'billTypeId': $scope.billTypeId,
            	'orgUnitNo': $scope.orgUnitNo,
                'pageNum': pageNum,
                'pageSize': pageSize
            },
        }).then(function successCallback(response) {
                $scope.conditionGridData = response.data.rows;               
                $scope.conditionTotalServerItems = response.data.total;
            }, function errorCallback(response) {
                // 请求失败执行代码
                $scope.conditionGridData = [];
                $scope.conditionTotalServerItems = 0;
        });
    }
    //表格属性配置
    $scope.conditionGridOptions = {  
        data: $scope.conditionGridDataName,
        multiSelect: false,//不可多选
        enablePaging: true,
        pagingOptions: $scope.conditionPagingOptions,
        totalServerItems: 'conditionTotalServerItems',
        i18n:'zh-cn',  
        showFooter: true,
        showSelectionCheckbox: false, 
        columnDefs : $scope.conditionColumnDataName,
        beforeSelectionChange: function (event) {
        	var conditionVal = event.entity.paramName;
        	if($scope.conditionExpressionName == '' || $scope.conditionExpressionName == undefined){
        		$scope.conditionExpressionName = conditionVal;
        	}else{
        		$scope.conditionExpressionName = $scope.conditionExpressionName + ' 且 ' + conditionVal;
        	}
            return true;
        }
    };
    
    $scope.getValueByName = function(name) {
    	//去换行
    	name = name.replace(/[\r\n]/g,"");
		//去空格
    	name = name.replace(/\s*/g,"");
    	//根据中文转化成表达式
    	if(name.indexOf('同意')>-1 || name.indexOf('拒绝')>-1){
    		if(name.indexOf('同意')>-1){
    			name = name.replace("同意","activityCondition.agree(opinion)");
    		}else if(name.indexOf('拒绝')>-1){
    			name = name.replace("拒绝","activityCondition.refuse(opinion)");
    		}
    	}else{
    		var nameArray = name.split(/且|或/);
        	for(var i = 0; i < nameArray.length; i++) {
        		var tempValue = '';
        		nameArray[i] = nameArray[i].replace(/\(/g,"");
        		nameArray[i] = nameArray[i].replace(/\)/g,"");
        		var conditionName = nameArray[i].replace("全部","");
        		if((conditionName.split('<=')).length-1 == 2){
        			//x<=y<=z
        			var mapValue = conditionName.split('<=')[1];
        			var mapKey = getMapKeyByValue(conditionMap,mapValue);
        			if(mapKey.indexOf('List') > -1){
        				if(nameArray[i].indexOf('全部') > -1){
        					tempValue = 'activityCondition.valueListAllBetween('+mapKey+','+conditionName.split('<=')[0]+','+conditionName.split('<=')[2]+')';
        				}else{
        					tempValue = 'activityCondition.valueListBetween('+mapKey+','+conditionName.split('<=')[0]+','+conditionName.split('<=')[2]+')';
        				}
        			}else{
        				tempValue = 'activityCondition.valueBetween('+mapKey+','+conditionName.split('<=')[0]+','+conditionName.split('<=')[2]+')';
        			}
        		}else if(conditionName.indexOf('<=') > -1 && conditionName.indexOf('<') > -1 && conditionName.lastIndexOf('<') > conditionName.indexOf('<=')){
        			//x<=y<z
        			var mapValue = conditionName.substring(conditionName.indexOf('<=')+2,conditionName.lastIndexOf('<'));
        			var mapKey = getMapKeyByValue(conditionMap,mapValue);
        			if(mapKey.indexOf('List') > -1){
        				if(nameArray[i].indexOf('全部') > -1){
        					tempValue = 'activityCondition.valueListAllBetween3('+mapKey+','+conditionName.substring(0,conditionName.indexOf('<='))+','+conditionName.substring(conditionName.lastIndexOf('<')+1)+')';
        				}else{
        					tempValue = 'activityCondition.valueListBetween3('+mapKey+','+conditionName.substring(0,conditionName.indexOf('<='))+','+conditionName.substring(conditionName.lastIndexOf('<')+1)+')';
        				}
        			}else{
        				tempValue = 'activityCondition.valueBetween3('+mapKey+','+conditionName.substring(0,conditionName.indexOf('<='))+','+conditionName.substring(conditionName.lastIndexOf('<')+1)+')';
        			}
        		}else if(conditionName.indexOf('<=') > -1 && conditionName.indexOf('<') > -1 && conditionName.indexOf('<=') > conditionName.indexOf('<')){
        			//x<y<=z
        			var mapValue = conditionName.substring(conditionName.indexOf('<')+1,conditionName.indexOf('<='));
        			var mapKey = getMapKeyByValue(conditionMap,mapValue);
        			if(mapKey.indexOf('List') > -1){
        				if(nameArray[i].indexOf('全部') > -1){
        					tempValue = 'activityCondition.valueListAllBetween4('+mapKey+','+conditionName.substring(0,conditionName.indexOf('<'))+','+conditionName.substring(conditionName.indexOf('<=')+2)+')';
        				}else{
        					tempValue = 'activityCondition.valueListBetween4('+mapKey+','+conditionName.substring(0,conditionName.indexOf('<'))+','+conditionName.substring(conditionName.indexOf('<=')+2)+')';
        				}
        			}else{
        				tempValue = 'activityCondition.valueBetween4('+mapKey+','+conditionName.substring(0,conditionName.indexOf('<'))+','+conditionName.substring(conditionName.indexOf('<=')+2)+')';
        			}
        		}else if((conditionName.split('<')).length-1 == 2){
        			//x<y<z
        			var mapValue = conditionName.split('<')[1];
        			var mapKey = getMapKeyByValue(conditionMap,mapValue);
        			if(mapKey.indexOf('List') > -1){
        				if(nameArray[i].indexOf('全部') > -1){
        					tempValue = 'activityCondition.valueListAllBetween2('+mapKey+','+conditionName.split('<')[0]+','+conditionName.split('<')[2]+')';
        				}else{
        					tempValue = 'activityCondition.valueListBetween2('+mapKey+','+conditionName.split('<')[0]+','+conditionName.split('<')[2]+')';
        				}
        			}else{
        				tempValue = 'activityCondition.valueBetween2('+mapKey+','+conditionName.split('<')[0]+','+conditionName.split('<')[2]+')';
        			}
        		}else if(conditionName.indexOf('notin') > -1){
        			//notin
        			nameArray[i] = nameArray[i].substring(0,nameArray[i].indexOf('notin'))+'notin('+nameArray[i].substring(nameArray[i].indexOf('notin')+'notin'.length)+')';
        			var mapValue = conditionName.substring(0,conditionName.indexOf('notin'));
        			var mapKey = getMapKeyByValue(conditionMap,mapValue);
        			if(mapKey.indexOf('List') > -1){
        				if(nameArray[i].indexOf('全部') > -1){
        					tempValue = 'activityCondition.valueListAllNotIn('+mapKey+',"'+conditionName.substring(conditionName.indexOf('notin')+'notin'.length)+'")';
        				}else{
        					tempValue = 'activityCondition.valueListNotIn('+mapKey+',"'+conditionName.substring(conditionName.indexOf('notin')+'notin'.length)+'")';
        				}
        			}else{
        				tempValue = 'activityCondition.valueNotIn('+mapKey+',"'+conditionName.substring(conditionName.indexOf('notin')+'notin'.length)+'")';
        			}
        		}else if(conditionName.indexOf('in') > -1){
        			//in
        			nameArray[i] = nameArray[i].substring(0,nameArray[i].indexOf('in'))+'in('+nameArray[i].substring(nameArray[i].indexOf('in')+'in'.length)+')';
        			var mapValue = conditionName.substring(0,conditionName.indexOf('in'));
        			var mapKey = getMapKeyByValue(conditionMap,mapValue);
        			if(mapKey.indexOf('List') > -1){
        				if(nameArray[i].indexOf('全部') > -1){
        					tempValue = 'activityCondition.valueListAllIn('+mapKey+',"'+conditionName.substring(conditionName.indexOf('in')+'in'.length)+'")';
        				}else{
        					tempValue = 'activityCondition.valueListIn('+mapKey+',"'+conditionName.substring(conditionName.indexOf('in')+'in'.length)+'")';
        				}
        			}else{
        				tempValue = 'activityCondition.valueIn('+mapKey+',"'+conditionName.substring(conditionName.indexOf('in')+'in'.length)+'")';
        			}
        		}else{
        			compareMap.forEach(function(value, key) {
        			    if(tempValue == '' && conditionName.indexOf(key) > -1){
        			    	if(key == '='){
        			    		if(conditionName.indexOf('>=') > -1){
        			    			key = '>=';
            			    		value = 'activityCondition.valueGe';
        			    		}else if(conditionName.indexOf('<=') > -1){
        			    			key = '<=';
            			    		value = 'activityCondition.valueLe';
        			    		}
        			    	}else if(key == '>' && conditionName.indexOf('>=') > -1){
        			    		key = '>=';
        			    		value = 'activityCondition.valueGe';
        			    	}else if(key == '<' && conditionName.indexOf('<=') > -1){
        			    		key = '<=';
        			    		value = 'activityCondition.valueLe';
        			    	}
        			    	var mapValue = conditionName.substring(0,conditionName.indexOf(key));
        			    	var mapKey = getMapKeyByValue(conditionMap,mapValue);
        			    	if(mapKey != ''){
        			    		var methodValue = value;
        			    		if(mapKey.indexOf('List') > -1){
        			    			if(nameArray[i].indexOf('全部') > -1){
        			    				methodValue = methodValue.replace("value","valueListAll");
        			    			}else{
        			    				methodValue = methodValue.replace("value","valueList");
        			    			}
        			    		}
        			    		tempValue = methodValue+'('+mapKey+','+conditionName.substring(conditionName.indexOf(key)+key.length)+')';
        			    	}else{
        			    		mapValue = conditionName.substring(conditionName.indexOf(key)+key.length);
        			    		mapKey = getMapKeyByValue(conditionMap,mapValue);
        			    		var methodValue = value;
        			    		if(methodValue.indexOf('activityCondition.valueG') > -1){
        			    			methodValue = value.replace("activityCondition.valueG","activityCondition.valueL");
        			    		}else if(methodValue.indexOf('activityCondition.valueL') > -1){
        			    			methodValue = value.replace("activityCondition.valueL","activityCondition.valueG");
        			    		}
        			    		if(mapKey.indexOf('List') > -1){
        			    			if(nameArray[i].indexOf('全部') > -1){
        			    				methodValue = methodValue.replace("value","valueListAll");
        			    			}else{
        			    				methodValue = methodValue.replace("value","valueList");
        			    			}
        			    		}
        			    		tempValue = methodValue+'('+mapKey+','+conditionName.substring(0,conditionName.indexOf(key))+')';
        			    	}
        			    }
        			});
        		}
        		if(tempValue != ''){
        			name = name.replace(nameArray[i],tempValue);
        		}
        	}
        	name = name.replace(/且/g,"&&");
        	name = name.replace(/或/g,"||");
    	}
    	$scope.conditionExpressionValue = '${'+name+"}";
    };
    
    $scope.getNameByValue = function(value) {
    	//根据表达式转化成中文
    	var transValue = value.substring(2,value.length-1);
    	if(transValue.indexOf('activityCondition.agree')>-1 || transValue.indexOf('activityCondition.refuse')>-1){
    		if(transValue.indexOf('activityCondition.agree')>-1){
    			transValue = transValue.replace("activityCondition.agree(opinion)","同意");
    		}else if(transValue.indexOf('activityCondition.refuse')>-1){
    			transValue = transValue.replace("activityCondition.refuse(opinion)","拒绝");
    		}
    	}else{
    		var transValueArray = transValue.split(/&&|[||]/);;
        	for(var i = 0; i < transValueArray.length; i++) {
        		var tempName = '';
        		if(transValueArray[i] != ''){
        			transValueArray[i] = replaceLeft(transValueArray[i]);
        			transValueArray[i] = replaceRight(transValueArray[i]);
        			//去换行
        			transValueArray[i] = transValueArray[i].replace(/[\r\n]/g,"");
        			//去空格
            		var conditionValue = transValueArray[i].replace("valueList","value").replace(/\s*/g,"");
            		var conditionKey = conditionValue.substring(conditionValue.indexOf("(")+1,conditionValue.indexOf(")"));
            		var conditionName = conditionMap.get(conditionKey.split(',')[0]);
            		if(conditionValue.indexOf("valueAll") > -1){
            			conditionValue = transValueArray[i].replace("valueListAll","value").replace(/\s*/g,"");
            			conditionName = '全部'+conditionName;
            		}
            		if(conditionValue.indexOf("valueBetween2") > -1){
            			conditionValue = conditionValue.substring(conditionValue.indexOf("(")+1,conditionValue.indexOf(")"));
            			tempName = conditionValue.split(',')[1]+'<'+conditionName+'<'+conditionValue.split(',')[2];
            		}else if(conditionValue.indexOf("valueBetween3") > -1){
            			conditionValue = conditionValue.substring(conditionValue.indexOf("(")+1,conditionValue.indexOf(")"));
            			tempName = conditionValue.split(',')[1]+'<='+conditionName+'<'+conditionValue.split(',')[2];
            		}else if(conditionValue.indexOf("valueBetween4") > -1){
            			conditionValue = conditionValue.substring(conditionValue.indexOf("(")+1,conditionValue.indexOf(")"));
            			tempName = conditionValue.split(',')[1]+'<'+conditionName+'<='+conditionValue.split(',')[2];
            		}else if(conditionValue.indexOf("valueBetween") > -1){
            			conditionValue = conditionValue.substring(conditionValue.indexOf("(")+1,conditionValue.indexOf(")"));
            			tempName = conditionValue.split(',')[1]+'<='+conditionName+'<='+conditionValue.split(',')[2];
            		}else if(conditionValue.indexOf("valueNotIn") > -1){
            			conditionValue = conditionValue.substring(conditionValue.indexOf("(")+1,conditionValue.indexOf(")"));
            			tempName = conditionName+' notin ('+conditionValue.substring(conditionValue.indexOf(",")+2,conditionValue.length-1)+')';
            		}else if(conditionValue.indexOf("valueIn") > -1){
            			conditionValue = conditionValue.substring(conditionValue.indexOf("(")+1,conditionValue.indexOf(")"));
            			tempName = conditionName+' in ('+conditionValue.substring(conditionValue.indexOf(",")+2,conditionValue.length-1)+')';
            		}else{
            			compareMap.forEach(function(value, key) {
            			    if(conditionValue.indexOf(value) > -1){
            			    	conditionValue = conditionValue.substring(conditionValue.indexOf("(")+1,conditionValue.indexOf(")"));
            			    	tempName = conditionName+key+conditionValue.split(',')[1];
            			    }
            			});
            		}
            		if(tempName != ''){
            			transValue = transValue.replace(transValueArray[i],tempName);
            		}
        		}
        	}
        	transValue = transValue.replace(/&&/g,"且");
        	transValue = transValue.replace(/[||]/g,"或");
        	transValue = transValue.replace(/或或/g,"或");
    	}
    	$scope.conditionExpressionName = transValue;
    }
    
    $scope.conditionExpressionValue = '';
    $scope.conditionExpressionName = '';
    
    // Put json representing condition on scope
    if ($scope.property.value !== undefined && $scope.property.value !== null) {
        $scope.conditionExpressionValue = $scope.property.value;
        if(conditionMap.size > 0){
        	$scope.getNameByValue($scope.conditionExpressionValue);
        }
    }
	
    $scope.save = function() {
    	if($scope.conditionExpressionName == ''){
    		 $scope.conditionExpressionValue = '';
    	}else if ($scope.conditionExpressionName !== undefined && $scope.conditionExpressionName !== null) {
    		$scope.getValueByName($scope.conditionExpressionName);
    	}
        $scope.property.value = $scope.conditionExpressionValue;
        $scope.updatePropertyInModel($scope.property);
        $scope.close();
    };

    // Close button handler
    $scope.close = function() {
    	$scope.property.mode = 'read';
    	$scope.$hide();
    };   
}];

function getMapKeyByValue(map, mapValue){
	var mapKey = '';
	map.forEach(function(value, key) {
	    if(mapValue == value){
	    	mapKey = key;
	    }
	});
    return mapKey;
}

function replaceLeft(str){ 
    if(str.indexOf('(activityCondition') > -1){ 
        return replaceLeft((str.replace('(activityCondition','activityCondition'))); 
    }else{ 
        return str; 
    } 
} 

function replaceRight(str){ 
    if(str.indexOf('))') > -1){ 
        return replaceRight((str.replace('))',')'))); 
    }else{ 
        return str; 
    } 
}
