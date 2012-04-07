#!/usr/bin/env groovy

import groovy.text.SimpleTemplateEngine

/**
 * Usage : cat data/test.txt | groovy run.groovy
 */

/**
 * configuration
 */
def fieldDefs = [
empno  : 4, 
''     : 3, 
deptno : 2
]

def sql = '''
select xx, ${columns} 
from <tablename> 
where  ${predicate} 
'''

/**
 * program
 */

def columns = ""
def predicate = ""

fieldDefs.each { k, v ->
    if (!(k =~ /^ *$/)) {
        columns += String.format("substring(col, %d) as %s,\n", v, k)
	}
}

def bindings = [ 'columns':columns.trim(), 'predicate':predicate ]

def engine = new SimpleTemplateEngine()
def t = engine.createTemplate(sql).make(bindings)

println t.toString()
