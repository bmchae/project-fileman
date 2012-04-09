import groovy.sql.Sql


def hiveSql = """
SELECT x from dual
"""

def mysqlSql = """
insert into test values(?)
"""

/**
 * program
 */
println 'add jar files ...'
[System.getenv()['HADOOP_HOME'],
System.getenv()['HADOOP_HOME']+'/lib',
System.getenv()['HIVE_HOME']+'/lib'].each { lib ->
   new File(lib).eachFileMatch(~/.*\.jar$/) {
       this.class.classLoader.rootLoader.addURL(it.toURL())
   }
}

println 'connection hive/mysql'
def mysqlConn = Sql.newInstance('jdbc:mysql://localhost:3306/bccard',
'huser', 'wjrm123', 'com.mysql.jdbc.Driver')
def hiveConn = Sql.newInstance('jdbc:hive://localhost:10000/default',
'', '', 'org.apache.hadoop.hive.jdbc.HiveDriver')

println 'from hive 2 mysql'
java.sql.Statement stmt = hiveConn.connection.createStatement()
java.sql.ResultSet rs = stmt.executeQuery(hiveSql)
while (rs.next()) {
   printf '%s\n', rs.getString(1)
   mysqlConn.execute(mysqlSql, [rs.getString(1)]);
}

