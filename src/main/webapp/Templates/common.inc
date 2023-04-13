<%-- 
Name:
Author: 
Description: 
--%>
 <script runat="template">
private Regex cleanRegEx = new Regex(@"\s+|_|-|\.", RegexOptions.Compiled);
private Regex cleanID = new Regex(@"(_ID|_id|_Id|\.ID|\.id|\.Id|ID|Id)", RegexOptions.Compiled);

public String Getter(string t,string name){
	if(t=="boolean"){
		return "is"+PascalCase(name);
	}
	return "get"+PascalCase(name);
}

public string CleanName(string name)
{
	return cleanRegEx.Replace(name, "");
}

public string CamelCase(string name)
{
	string output = CleanName(name);
	return char.ToLower(output[0]) + output.Substring(1);
}

public string PascalCase(string name)
{
	string output = CleanName(name);
	return char.ToUpper(output[0]) + output.Substring(1);
}

public string MakePlural(string name)
{
	Regex plural1 = new Regex("(?<keep>[^aeiou])y$");
	Regex plural2 = new Regex("(?<keep>[aeiou]y)$");
	Regex plural3 = new Regex("(?<keep>[sxzh])$");
	Regex plural4 = new Regex("(?<keep>[^sxzhy])$");

	if(plural1.IsMatch(name))
		return plural1.Replace(name, "${keep}ies");
	else if(plural2.IsMatch(name))
		return plural2.Replace(name, "${keep}s");
	else if(plural3.IsMatch(name))
		return plural3.Replace(name, "${keep}es");
	else if(plural4.IsMatch(name))
		return plural4.Replace(name, "${keep}s");

	return name;
}

public string MakeSingle(string name)
{
	Regex plural1 = new Regex("(?<keep>[^aeiou])ies$");
	Regex plural2 = new Regex("(?<keep>[aeiou]y)s$");
	Regex plural3 = new Regex("(?<keep>[sxzh])es$");
	Regex plural4 = new Regex("(?<keep>[^sxzhyu])s$");

	if(plural1.IsMatch(name))
		return plural1.Replace(name, "${keep}y");
	else if(plural2.IsMatch(name))
		return plural2.Replace(name, "${keep}");
	else if(plural3.IsMatch(name))
		return plural3.Replace(name, "${keep}");
	else if(plural4.IsMatch(name))
		return plural4.Replace(name, "${keep}");

	return name;
}

public bool IsManyToManyTable(TableSchema table)
{
	if (table.Columns.Count == 2 && table.PrimaryKey != null && table.PrimaryKey.MemberColumns.Count == 2 && table.ForeignKeys.Count == 2)
//	if (table.Columns.Count >= 2 && table.PrimaryKey != null && table.PrimaryKey.MemberColumns.Count == 2 && table.ForeignKeys.Count >= 2)
		return true;
	else
		return false;
}

public bool IsOneToOneTable(TableKeySchema primaryKey)
{
	if (primaryKey.ForeignKeyMemberColumns[0].IsPrimaryKeyMember)
	{
		// Check if the current table is the primary key with one-to-one mappings to several tables.
		// If so, generate polymorphic classes using joined sub-classes & corresponding C# classes.
		int count = 0;
		foreach(TableKeySchema pk in SourceTable.PrimaryKeys)
		{
			// check if other end of the link is the primary key for the table (if count > 1 then it may
			// be a composite primary key and most likely a many-to-many link table which can be ignored)
			if (pk.ForeignKeyMemberColumns[0].IsPrimaryKeyMember && pk.ForeignKeyTable.PrimaryKey.MemberColumns.Count == 1)
				count++;
		}
		if (count > 1)
			return false;

		return true;
	}
	return false;
}

public bool IsSubClassTable(TableKeySchema primaryKey)
{
	if (primaryKey.ForeignKeyMemberColumns[0].IsPrimaryKeyMember)
	{
		// Check if the current table is the primary key with one-to-one mappings to several tables.
		// If so, generate polymorphic classes using joined sub-classes & corresponding C# classes.
		int count = 0;
		foreach(TableKeySchema pk in SourceTable.PrimaryKeys)
		{
			// check if other end of the link is the primary key for the table (if count > 1 then it may
			// be a composite primary key and most likely a many-to-many link table which can be ignored)
			if (pk.ForeignKeyMemberColumns[0].IsPrimaryKeyMember && pk.ForeignKeyTable.PrimaryKey.MemberColumns.Count == 1)
				count++;
		}
		if (count > 1)
			return true;

		return false;
	}
	return false;
}

public string JavaType(ColumnSchema column)
{
	if (column.Name.EndsWith("TypeCode")) return column.Name;

	switch (column.DataType)
	{
		case DbType.AnsiString: return "String";
		case DbType.AnsiStringFixedLength: return "String";
		case DbType.Binary: return "byte[]";
		case DbType.Boolean: return "boolean";
		case DbType.Byte: return "byte";
		case DbType.Currency: return "BigDecimal";
		case DbType.Date: return "Date";
		case DbType.DateTime: return "Date";
		case DbType.Decimal: return "BigDecimal";
		case DbType.Double: return "double";
		case DbType.Guid: return "java.util.UUID";
		case DbType.Int16: return "short";
		case DbType.Int32: return "int";
		case DbType.Int64: return "long";
		case DbType.Object: return "object";
		case DbType.SByte: return "sbyte";
		case DbType.Single: return "float";
		case DbType.String: return "String";
		case DbType.StringFixedLength: return "String";
		case DbType.Time: return "java.util.Date";
		case DbType.UInt16: return "short";
		case DbType.UInt32: return "int";
		case DbType.UInt64: return "long";
		case DbType.VarNumeric: return "BigDecimal";
		default:
		{
			return "__UNKNOWN__" + column.NativeType;
		}
	}
}
public string FieldType(ColumnSchema column)
{
	if (column.Name.EndsWith("TypeCode")) return column.Name;

	switch (column.DataType)
	{
		case DbType.AnsiString: return "TextField";
		case DbType.AnsiStringFixedLength: return "TextField"; 
		case DbType.Boolean: return "CheckBox"; 
		case DbType.Currency: return "NumberField";
		case DbType.Date: return "LocalDateTimeField";
		case DbType.DateTime: return "LocalDateTimeField";
		case DbType.Decimal: return "NumberField";
		case DbType.Double: return "NumberField"; 
		case DbType.Single: return "NumberField";
		case DbType.String: return "TextField";
		case DbType.StringFixedLength: return "TextField";
		case DbType.Time: return "LocalDateTimeField"; 
		case DbType.Int16: return "NumberField";
		case DbType.Int32: return "NumberField";
		case DbType.Int64: return "NumberField";
		case DbType.UInt16: return "NumberField";
		case DbType.UInt32: return "NumberField";
		case DbType.UInt64: return "NumberField";
		default:
		{
			return "TextField";
		}
	}
}
public string CSharpType(ColumnSchema column)
{
	if (column.Name.EndsWith("TypeCode")) return column.Name;

	switch (column.DataType)
	{
		case DbType.AnsiString: return "string";
		case DbType.AnsiStringFixedLength: return "string";
		case DbType.Binary: return "byte[]";
		case DbType.Boolean: return "bool";
		case DbType.Byte: return "byte";
		case DbType.Currency: return "decimal";
		case DbType.Date: return "DateTime";
		case DbType.DateTime: return "DateTime";
		case DbType.Decimal: return "decimal";
		case DbType.Double: return "double";
		case DbType.Guid: return "Guid";
		case DbType.Int16: return "short";
		case DbType.Int32: return "int";
		case DbType.Int64: return "long";
		case DbType.Object: return "object";
		case DbType.SByte: return "sbyte";
		case DbType.Single: return "float";
		case DbType.String: return "string";
		case DbType.StringFixedLength: return "string";
		case DbType.Time: return "TimeSpan";
		case DbType.UInt16: return "ushort";
		case DbType.UInt32: return "uint";
		case DbType.UInt64: return "ulong";
		case DbType.VarNumeric: return "decimal";
		default:
		{
			return "__UNKNOWN__" + column.NativeType;
		}
	}
}


   public   String attrExpr(String att, String value) {
        if (value==null||value.Length==0) {
            return "";
        }
        return " "+att + "=\"" + value + "\"";
    }

    public   String attrExpr(String att, bool  val) {
        return " "+att + "=\"" + val + "\"";
    }




 public String attrExpr(String attr,bool val,bool defaultValue){
        if(val==defaultValue)
            return "";
        return " "+attr + "=\"" + val + "\"";
    }
	
public String newLine(String lineContent){ 
	 return lineContent+"\n";
}

public string HibernateType(ColumnSchema column)
{
	if (column.Name.EndsWith("TypeCode")) return column.Name;

	switch (column.DataType)
	{
		case DbType.AnsiString: return "string";
		case DbType.AnsiStringFixedLength: return "string";
		case DbType.Binary: return "binary";
		case DbType.Boolean: return "boolean";
		case DbType.Byte: return "byte";
		case DbType.Currency: return "big_decimal";
		case DbType.Date: return "date";
		case DbType.DateTime: return "date";
		case DbType.Decimal: return "big_decimal";
		case DbType.Double: return "double";
		case DbType.Guid: return "string";
		case DbType.Int16: return "short";
		case DbType.Int32: return "integer";
		case DbType.Int64: return "long"; 
		case DbType.SByte: return "byte";
		case DbType.Single: return "float";
		case DbType.String: return "string";
		case DbType.StringFixedLength: return "string";
		case DbType.Time: return "time";
		case DbType.UInt16: return "short";
		case DbType.UInt32: return "integer";
		case DbType.UInt64: return "long";
		case DbType.VarNumeric: return "big_decimal";
		default:
		{
			return "__UNKNOWN__" + column.NativeType;
		}
	}
}

private Regex sqlCharacters = new Regex(@"[\s|~|-|!|{|%|}|\^|'|&|.|\(|\\|\)|`]", RegexOptions.Compiled);
private Regex sqlReserved = new Regex(@"^(ADD|EXCEPT|PERCENT|ALL|EXEC|PLAN|ALTER|EXECUTE|PRECISION|AND|EXISTS|PRIMARY|ANY|EXIT|PRINT|AS|FETCH|PROC|ASC|FILE|PROCEDURE|AUTHORIZATION|FILLFACTOR|PUBLIC|BACKUP|FOR|RAISERROR|BEGIN|FOREIGN|READ|BETWEEN|FREETEXT|READTEXT|BREAK|FREETEXTTABLE|RECONFIGURE|BROWSE|FROM|REFERENCES|BULK|FULL|REPLICATION|BY|FUNCTION|RESTORE|CASCADE|GOTO|RESTRICT|CASE|GRANT|RETURN|CHECK|GROUP|REVOKE|CHECKPOINT|HAVING|RIGHT|CLOSE|HOLDLOCK|ROLLBACK|CLUSTERED|IDENTITY|ROWCOUNT|COALESCE|IDENTITY_INSERT|ROWGUIDCOL|COLLATE|IDENTITYCOL|RULE|COLUMN|IF|SAVE|COMMIT|IN|SCHEMA|COMPUTE|INDEX|SELECT|CONSTRAINT|INNER|SESSION_USER|CONTAINS|INSERT|SET|CONTAINSTABLE|INTERSECT|SETUSER|CONTINUE|INTO|SHUTDOWN|CONVERT|IS|SOME|CREATE|JOIN|STATISTICS|CROSS|KEY|SYSTEM_USER|CURRENT|KILL|TABLE|CURRENT_DATE|LEFT|TEXTSIZE|CURRENT_TIME|LIKE|THEN|CURRENT_TIMESTAMP|LINENO|TO|CURRENT_USER|LOAD|TOP|CURSOR|NATIONAL||TRAN|DATABASE|NOCHECK|TRANSACTION|DBCC|NONCLUSTERED|TRIGGER|DEALLOCATE|NOT|TRUNCATE|DECLARE|NULL|TSEQUAL|DEFAULT|NULLIF|UNION|DELETE|OF|UNIQUE|DENY|OFF|UPDATE|DESC|OFFSETS|UPDATETEXT|DISK|ON|USE|DISTINCT|OPEN|USER|DISTRIBUTED|OPENDATASOURCE|VALUES|DOUBLE|OPENQUERY|VARYING|DROP|OPENROWSET|VIEW|DUMMY|OPENXML|WAITFOR|DUMP|OPTION|WHEN|ELSE|OR|WHERE|END|ORDER|WHILE|ERRLVL|OUTER|WITH|ESCAPE|OVER|WRITETEXT)$", RegexOptions.Compiled | RegexOptions.IgnoreCase);
private Regex sqlFuture = new Regex(@"^(ABSOLUTE|FOUND|PRESERVE|ACTION|FREE|PRIOR|ADMIN|GENERAL|PRIVILEGES|AFTER|GET|READS|AGGREGATE|GLOBAL|REAL|ALIAS|GO|RECURSIVE|ALLOCATE|GROUPING|REF|ARE|HOST|REFERENCING|ARRAY|HOUR|RELATIVE|ASSERTION|IGNORE|RESULT|AT|IMMEDIATE|RETURNS|BEFORE|INDICATOR|ROLE|BINARY|INITIALIZE|ROLLUP|BIT|INITIALLY|ROUTINE|BLOB|INOUT|ROW|BOOLEAN|INPUT|ROWS|BOTH|INT|SAVEPOINT|BREADTH|INTEGER|SCROLL|CALL|INTERVAL|SCOPE|CASCADED|ISOLATION|SEARCH|CAST|ITERATE|SECOND|CATALOG|LANGUAGE|SECTION|CHAR|LARGE|SEQUENCE|CHARACTER|LAST|SESSION|CLASS|LATERAL|SETS|CLOB|LEADING|SIZE|COLLATION|LESS|SMALLINT|COMPLETION|LEVEL|SPACE|CONNECT|LIMIT|SPECIFIC|CONNECTION|LOCAL|SPECIFICTYPE|CONSTRAINTS|LOCALTIME|SQL|CONSTRUCTOR|LOCALTIMESTAMP|SQLEXCEPTION|CORRESPONDING|LOCATOR|SQLSTATE|CUBE|MAP|SQLWARNING|CURRENT_PATH|MATCH|START|CURRENT_ROLE|MINUTE|STATE|CYCLE|MODIFIES|STATEMENT|DATA|MODIFY|STATIC|DATE|MODULE|STRUCTURE|DAY|MONTH|TEMPORARY|DEC|NAMES|TERMINATE|DECIMAL|NATURAL|THAN|DEFERRABLE|NCHAR|TIME|DEFERRED|NCLOB|TIMESTAMP|DEPTH|NEW|TIMEZONE_HOUR|DEREF|NEXT|TIMEZONE_MINUTE|DESCRIBE|NO|TRAILING|DESCRIPTOR|NONE|TRANSLATION|DESTROY|NUMERIC|TREAT|DESTRUCTOR|OBJECT|TRUE|DETERMINISTIC|OLD|UNDER|DICTIONARY|ONLY|UNKNOWN|DIAGNOSTICS|OPERATION|UNNEST|DISCONNECT|ORDINALITY|USAGE|DOMAIN|OUT|USING|DYNAMIC|OUTPUT|VALUE|EACH|PAD|VARCHAR|END-EXEC|PARAMETER|VARIABLE|EQUALS|PARAMETERS|WHENEVER|EVERY|PARTIAL|WITHOUT|EXCEPTION|PATH|WORK|EXTERNAL|POSTFIX|WRITE|FALSE|PREFIX|YEAR|FIRST|PREORDER|ZONE|FLOAT|PREPARE)$", RegexOptions.Compiled | RegexOptions.IgnoreCase);

public string SqlIdentifier(string sqlIdentifier)
{
	if (sqlCharacters.IsMatch(sqlIdentifier) || sqlReserved.IsMatch(sqlIdentifier) || sqlFuture.IsMatch(sqlIdentifier))
		return String.Format("`{0}`", sqlIdentifier);
	else
		return sqlIdentifier;
}
 
 
  
public string ClassTable(TableSchema table)
{
	return table.Name;
}
public string ClassTableAtt(TableSchema table)
{
	return String.Format(" table=\"{0}\"", SqlIdentifier(table.Name));
}
 
 
public string IdMemberType(TableSchema table)
{
	return MemberType(table.PrimaryKey.MemberColumns[0]);
}
public string IdType(TableSchema table)
{
	return PropertyType(table.PrimaryKey.MemberColumns[0]);
}
public string IdTypeAtt(TableSchema table)
{
	return String.Format(" type=\"{0}\"", PropertyType(table.PrimaryKey.MemberColumns[0]));
}
public string IdUnsavedValueAtt(TableSchema table)
{
	ColumnSchema column = table.PrimaryKey.MemberColumns[0];
	if (column.Size == 0)
		return String.Format(" unsaved-value=\"{0}\"", 0);
	else
		return String.Format(" unsaved-value=\"{0}\"", "null");
}

public string PropertyName(ColumnSchema column)
{
	return PascalCase(column.Name);
}
public string MemberName(ColumnSchema column)
{
	return "_" + CamelCase(column.Name);
}
public string ParameterName(ColumnSchema column)
{
	return CamelCase(column.Name);
}
public string PropertyNameAtt(ColumnSchema column)
{
	return String.Format(" name=\"{0}\"", PropertyName(column));
}
public string PropertyType(ColumnSchema column)
{
	return HibernateType(column);
}
public string MemberType(ColumnSchema column)
{
	return CSharpType(column);
}
public string PropertyTypeAtt(ColumnSchema column)
{
	return String.Format(" type=\"{0}\"", PropertyType(column));
}

public string ColumnName(ColumnSchema column)
{
	return column.Name;
}
public string ColumnNameAtt(ColumnSchema column)
{
	return String.Format(" name=\"{0}\"", SqlIdentifier(ColumnName(column)));
}
public string ColumnLength(ColumnSchema column)
{
	if (column.Size > 0)
		return column.Size.ToString();
	else
		return String.Empty;
}
public string ColumnLengthAtt(ColumnSchema column)
{
	if (column.Size > 0)
		return String.Format(" length=\"{0}\"", column.Size);
	else
		return String.Empty;
}
public string ColumnSqlTypeAtt(ColumnSchema column)
{
	return String.Format(" sql-type=\"{0}\"", column.NativeType);
}
public string ColumnNotNullAtt(ColumnSchema column)
{
	return String.Format(" not-null=\"{0}\"", (!column.AllowDBNull).ToString().ToLower());
}
public string ColumnUniqueAtt(ColumnSchema column)
{
	if (column.IsUnique)
		return String.Format(" unique=\"{0}\"", column.IsUnique.ToString().ToLower());
	else
		return String.Empty;
}
public string ColumnIndexAtt(TableSchema table, ColumnSchema column)
{
	foreach(IndexSchema index in table.Indexes)
	{
		if (index.MemberColumns.Contains(column))
		{
			return String.Format(" index=\"{0}\"", index.Name);
		}
	}
	return String.Empty;
}

public bool isIdentity(ColumnSchema cs){
	if(cs.ExtendedProperties["CS_IsIdentity"]==null)
		return false;
 	return (bool)cs.ExtendedProperties["CS_IsIdentity"].Value;

}


public String getPropStrValue(ColumnSchema cs,String attr){
	if(cs.ExtendedProperties[attr]==null)
		return null;
 	return (String)cs.ExtendedProperties[attr].Value;

}
</script>

