import groovy.sql.Sql 


def hiveSql = """
SELECT empno, ename, sal 
FROM emp
where 1=1
"""

def mysqlSql = """
insert into emp values(?,?)
"""

/**
 * program
 */
['HADOOP_HOME', 'HIVE_HOME'].each { home ->
    new File(System.getenv()[home] + '/lib').eachFileMatch(~/.*\.jar$/) {
        println 'add jar : ' + it.toURL()
        this.class.classLoader.rootLoader.addURL(it.toURL())
    }
}

hiveConn = Sql.newInstance('jdbc:hive://localhost:10000/default', '', '', 'org.apache.hadoop.hive.jdbc.HiveDriver')

def conn = Sql.newInstance("jdbc:mysql://localhost/test", "user", "pass", "com.mysql.jdbc.Driver")

hiveConn.eachRow(hiveSql) { e ->
    printf '%5d %s\n', e.empno, e.ename
	mysqlConn.execute(mysqlSql, [e.empno, e.ename]);
}



