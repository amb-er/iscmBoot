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
 * Assignment
 */
var KisBpmAssignmentCtrl = [ '$scope', '$modal', function($scope, $modal) {

    // Config for the modal window
    var opts = {
        template:  'editor-app/configuration/properties/assignment-popup.html?version=' + Date.now(),
        scope: $scope
    };

    // Open the dialog
    $modal(opts);
}];

var KisBpmAssignmentPopupCtrl = [ '$scope', '$http', function($scope, $http) {
    	
    // Put json representing assignment on scope
    if ($scope.property.value !== undefined && $scope.property.value !== null
        && $scope.property.value.assignment !== undefined
        && $scope.property.value.assignment !== null) 
    {
        $scope.assignment = $scope.property.value.assignment;
        $scope.assignment.assignee = '';
    } else {
        $scope.assignment = {};
    }

    if ($scope.assignment.candidateUsers == undefined || $scope.assignment.candidateUsers.length == 0)
    {
    	$scope.assignment.candidateUsers = [{value: ''}];
    }
    
    // Click handler for + button after enum value
    var userValueIndex = 1;
    $scope.addCandidateUserValue = function(index) {
        $scope.assignment.candidateUsers.splice(index + 1, 0, {value: ''});
    };

    // Click handler for - button after enum value
    $scope.removeCandidateUserValue = function(index) {
        $scope.assignment.candidateUsers.splice(index, 1);
        if ($scope.assignment.candidateUsers == undefined || $scope.assignment.candidateUsers.length == 0)
        {
        	$scope.assignment.candidateUsers = [{value: ''}];
        }
    };
    
    if ($scope.assignment.candidateGroups == undefined || $scope.assignment.candidateGroups.length == 0)
    {
    	$scope.assignment.candidateGroups = [{value: ''}];
    }
    
    var groupValueIndex = 1;
    $scope.addCandidateGroupValue = function(index) {
        $scope.assignment.candidateGroups.splice(index + 1, 0, {value: ''});
    };

    // Click handler for - button after enum value
    $scope.removeCandidateGroupValue = function(index) {
        $scope.assignment.candidateGroups.splice(index, 1);
        if ($scope.assignment.candidateGroups == undefined || $scope.assignment.candidateGroups.length == 0)
        {
        	$scope.assignment.candidateGroups = [{value: ''}];
        }
    };
    
    var dynamicUserIndex = -1;
    $scope.setDynamicUser = function(event,index) {
    	var action = event.target;
    	if(action.checked){
    		$scope.selectTitle = '选择动态用户'; 
    		$scope.selectType = 3;
        	dynamicUserIndex = index;
    	}else{
    		$scope.selectTitle = '选择候选人'; 
    		$scope.selectType = 1;
    		dynamicUserIndex = -1;
    		var userValue = $scope.assignment.candidateUsers[index].value;
        	if(userValue.indexOf('${') > -1){
        		$scope.assignment.candidateUsers[index].value = '';
        	}
    	}
    };
    
    $scope.isDynamic = function(index) {
    	var userValue = $scope.assignment.candidateUsers[index].value;
    	if(userValue.indexOf('${') > -1){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    var dynamicGroupIndex = -1;
    $scope.filterGroup = function(event,index) {
    	var action = event.target;
    	if(action.checked){
    		$scope.selectTitle = '选择过滤组织';   
    		$scope.selectType = 4;
    		dynamicGroupIndex = index;
    	}else {
    		$scope.selectTitle = '选择候选组';   
    		$scope.selectType = 2;
    		dynamicGroupIndex = -1;
    		var groupValue = $scope.assignment.candidateGroups[index].value;
    		if(groupValue.indexOf('UnitNo}_') > -1){
    			$scope.assignment.candidateGroups[index].value = groupValue.substring(groupValue.indexOf('UnitNo}_')+'UnitNo}_'.length);
    		}
    	}
    };
    
    $scope.isFilter = function(index) {
    	var groupValue = $scope.assignment.candidateGroups[index].value;
    	if(groupValue.indexOf('UnitNo}_') > -1){
    		return true;
    	}else{
    		return false;
    	}
    }

    $scope.save = function() {

        $scope.property.value = {};
        handleAssignmentInput($scope);
        $scope.property.value.assignment = $scope.assignment;
        
        $scope.updatePropertyInModel($scope.property);
        $scope.close();
    };

    // Close button handler
    $scope.close = function() {
    	handleAssignmentInput($scope);
    	$scope.property.mode = 'read';
    	$scope.$hide();
    };
    
    var handleAssignmentInput = function($scope) {
    	if ($scope.assignment.candidateUsers)
    	{
	    	var emptyUsers = true;
	    	var toRemoveIndexes = [];
	        for (var i = 0; i < $scope.assignment.candidateUsers.length; i++)
	        {
	        	if ($scope.assignment.candidateUsers[i].value != '')
	        	{
	        		emptyUsers = false;
	        	}
	        	else
	        	{
	        		toRemoveIndexes[toRemoveIndexes.length] = i;
	        	}
	        }
	        
	        for (var i = 0; i < toRemoveIndexes.length; i++)
	        {
	        	$scope.assignment.candidateUsers.splice(toRemoveIndexes[i], 1);
	        }
	        
	        if (emptyUsers)
	        {
	        	$scope.assignment.candidateUsers = undefined;
	        }
    	}
        
    	if ($scope.assignment.candidateGroups)
    	{
	        var emptyGroups = true;
	        var toRemoveIndexes = [];
	        for (var i = 0; i < $scope.assignment.candidateGroups.length; i++)
	        {
	        	if ($scope.assignment.candidateGroups[i].value != '')
	        	{
	        		emptyGroups = false;
	        	}
	        	else
	        	{
	        		toRemoveIndexes[toRemoveIndexes.length] = i;
	        	}
	        }
	        
	        for (var i = 0; i < toRemoveIndexes.length; i++)
	        {
	        	$scope.assignment.candidateGroups.splice(toRemoveIndexes[i], 1);
	        }
	        
	        if (emptyGroups)
	        {
	        	$scope.assignment.candidateGroups = undefined;
	        }
    	}
    };
    
    
    /*---------------------流程设计器扩展用户与用户组--------------------*/
    
    //参数初始化
    $scope.gridData = [];//表格数据
    $scope.gridDataName = 'gridData';
    $scope.selectTitle = '选择候选人';
    //表格列数据
    $scope.columnData = [ 
        {
        	field : 'code',
        	displayName : '用户编码',
        	minWidth : 100,
        	width : '20%'
        }, 
        {
        	field : 'name',
        	displayName : '用户名',
        	minWidth : 100,
        	width : '25%'
        }, 
        {
        	field : 'cellphone',
        	displayName : '电话',
        	minWidth : 100,
        	width : '25%'
        }, 
        {
        	field : 'email',
        	displayName : '邮箱',
        	minWidth : 100,
        	width : '30%'
        }
	];
    $scope.columnDataName = 'columnData';
    $scope.selectType = 1;// 0-代理人，1-候选人，2-候选组，3-动态候选人，4-组织过滤候选组
    $scope.totalServerItems = 0;// 表格总条数
    var a = window.location.search.substring(1).split("&")[2];
    $scope.orgUnitNo = a.substring(10);// 组织
    var b = window.location.search.substring(1).split("&")[3];
    $scope.billTypeId = b.substring(11);// 单据Id
    // 分页初始化
    $scope.pagingOptions = {
        pageSizes: [10, 20, 30],// page 行数可选值
        pageSize: 10, // 每页行数
        currentPage: 1, // 当前显示页数
    };
    
    $http({
        method: 'POST',
        url: ACTIVITI.CONFIG.contextRoot+'/model/getUserList',
        params:{
        	'billTypeId': $scope.billTypeId,
        	'orgUnitNo': $scope.orgUnitNo,
            'pageNum': $scope.pagingOptions.currentPage,
            'pageSize': $scope.pagingOptions.pageSize
        },
    }).then(function successCallback(response) {
            $scope.gridData = response.data.rows;               
            $scope.totalServerItems = response.data.total;
        }, function errorCallback(response) {
            // 请求失败执行代码
            $scope.gridData = [];
            $scope.totalServerItems = 0;
    });
    //分页数据监视
    $scope.$watch('pagingOptions', function (newValue, oldValue) {
        $scope.getPagedDataAsync($scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);    
    },true); 
    
    //当切换类型时，初始化当前页
    $scope.$watch('selectType', function (newValue, oldValue) {
        if(newValue != oldValue){
            $scope.pagingOptions.currentPage = 1; 
            $scope.getPagedDataAsync($scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
        }
    },true);
    
    //异步请求表格数据
    $scope.getPagedDataAsync = function(pageNum, pageSize){      
        var url;
        if($scope.selectType == 2){
            url = '/model/getGroupList';
            $scope.columnData = $scope.groupColumns;
        }else if($scope.selectType == 0 || $scope.selectType == 1){
            url = '/model/getUserList';
            $scope.columnData = $scope.userColumns;
        }else if($scope.selectType == 3){
            url = '/model/getDynamicUserList';
            $scope.columnData = $scope.dynamicUserColumns;
        }else if($scope.selectType == 4){
            url = '/model/getFilterOrgUnitNoList';
            $scope.columnData = $scope.filterOrgUnitNoColumns;
        }
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
                $scope.gridData = response.data.rows;               
                $scope.totalServerItems = response.data.total;
            }, function errorCallback(response) {
                // 请求失败执行代码
                $scope.gridData = [];
                $scope.totalServerItems = 0;
        });
    }
    //表格属性配置
    $scope.gridOptions = {  
        data: $scope.gridDataName,
        multiSelect: false,//不可多选
        enablePaging: false,
        pagingOptions: $scope.pagingOptions,
        totalServerItems: 'totalServerItems',
        i18n:'zh-cn',  
        showFooter: true,
        showFilter:true,
        showSelectionCheckbox: false, 
        columnDefs : $scope.columnDataName,
        beforeSelectionChange: function (event) {
            if($scope.selectType == 0){//选代理人
                event.entity.checked = !event.selected;
                $scope.assignment.assignee = event.entity.code;
            }else if($scope.selectType == 1){//候选人
                var obj = {value: event.entity.code};
                var userFlag = false;
                if(!array_contain($scope.assignment.candidateUsers, obj.value) && $scope.assignment.assignee != event.entity.code){
                	for (var i = 0; i < $scope.assignment.candidateUsers.length; i++){
                		if($scope.assignment.candidateUsers[i].value == ''){
                			$scope.assignment.candidateUsers[i].value = event.entity.code;
                			userFlag = true;
                			break;
                		}
                	}
                	if(!userFlag){
                		$scope.assignment.candidateUsers.push(obj);
                	}
                }                                   
            }else if($scope.selectType == 2){//候选组
                var obj = {value: event.entity.id+''};
                var groupFlag = false;
                if(!array_contain($scope.assignment.candidateGroups, obj.value)){
                	for (var i = 0; i < $scope.assignment.candidateGroups.length; i++){
                		if($scope.assignment.candidateGroups[i].value == ''){
                			$scope.assignment.candidateGroups[i].value = event.entity.id+'';
                			groupFlag = true;
                			break;
                		}
                	}
                	if(!groupFlag){
                		$scope.assignment.candidateGroups.push(obj);
                	}
                } 
            }else if($scope.selectType == 3){//动态候选人
                var obj = {value: '${'+event.entity.paramCode+'}'};
                if(!array_contain($scope.assignment.candidateUsers, obj.value) && $scope.assignment.assignee != obj.value && dynamicUserIndex > -1){
                	$scope.assignment.candidateUsers[dynamicUserIndex].value = obj.value;
                }                                   
            }else if($scope.selectType == 4){//组织过滤组
                var obj = {value: '${'+event.entity.paramCode+'}'};
                if(!array_contain($scope.assignment.candidateGroups, obj.value) && dynamicGroupIndex > -1){
                	var groupValue = $scope.assignment.candidateGroups[dynamicGroupIndex].value;
                	if(groupValue.indexOf('UnitNo}_') > -1){
                		$scope.assignment.candidateGroups[dynamicGroupIndex].value = obj.value+'_'+groupValue.substring(groupValue.indexOf('UnitNo}_')+'UnitNo}_'.length);
                	}else{
                		$scope.assignment.candidateGroups[dynamicGroupIndex].value = obj.value+'_'+groupValue;
                	}
                }                                   
            }
            return true;
        }
    };
    
    //选择用户时表头
    $scope.userColumns = [          
        {  
            field : 'code',  
            displayName : '用户编码',  
            minWidth: 100,  
            width : '20%'  
        },            
        {  
            field : 'name',  
            displayName : '用户名',  
            minWidth: 100,  
            width : '25%'  
        },  
        {  
            field : 'cellphone',  
            displayName : '电话',  
            minWidth: 100,  
            width : '25%'  
        },  
        {  
            field : 'email',   
            displayName : '邮箱',  
            minWidth: 100,  
            width : '30%'  
        }       
    ];
    //选择用户组时表头
    $scope.groupColumns = [          
        {  
            field : 'id',  
            type:'number',  
            displayName : '角色Id',  
            minWidth: 100,  
            width : '20%'  
        }, 
        {  
            field : 'name',   
            displayName : '角色名称',  
            minWidth: 100,  
            width : '35%'  
        }, 
        {  
        	field : 'remarks',   
            displayName : '备注',  
            minWidth: 100,  
            width : '45%'  
        }
    ];
    
    //选择动态用户时表头
    $scope.dynamicUserColumns = [          
        {  
            field : 'paramCode',  
            displayName : '用户字段',  
            minWidth: 100,  
            width : '20%'  
        },            
        {  
            field : 'paramName',  
            displayName : '名称',  
            minWidth: 100,  
            width : '25%'  
        },  
        {  
            field : 'subTable',  
            displayName : '字段类型',  
            minWidth: 100,  
            width : '25%',
            cellTemplate : '<span>{{!row.entity.subTable?"主表":"明细"}}</span>'
        },  
        {  
            field : 'remarks',   
            displayName : '备注',  
            minWidth: 100,  
            width : '30%'  
        }       
    ];
    
   //选择组织过滤时表头
    $scope.filterOrgUnitNoColumns = [          
        {  
            field : 'paramCode',  
            displayName : '组织字段',  
            minWidth: 100,  
            width : '20%'  
        },            
        {  
            field : 'paramName',  
            displayName : '名称',  
            minWidth: 100,  
            width : '25%'  
        },  
        {  
            field : 'subTable',  
            displayName : '字段类型',  
            minWidth: 100,  
            width : '25%',
            cellTemplate : '<span>{{!row.entity.subTable?"主表":"明细"}}</span>'
        },  
        {  
            field : 'remarks',   
            displayName : '备注',  
            minWidth: 100,  
            width : '30%'  
        }       
    ];
    
    //代理人(审批人)
    $scope.selectAssignee = function () {
        $scope.selectType = 0;
        $scope.selectTitle = '选择代理人';
    };
    
    //候选人
    $scope.selectCandidate = function () {
        $scope.selectType = 1;
        $scope.selectTitle = '选择候选人';   
    };
    
    //候选组
    $scope.selectGroup = function () {
        $scope.selectType = 2;
        $scope.selectTitle = '选择候选组';
    };
}];
//声明----如果有此 contains 直接用最好
function array_contain(array, obj){
    for (var i = 0; i < array.length; i++){
        if (array[i].value == obj)//如果要求数据类型也一致，这里可使用恒等号===
            return true;
    }
    return false;
}