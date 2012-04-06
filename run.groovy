#!/usr/bin/env groovy

/**
 * Usage : cat data/test.txt | groovy run.groovy
 */

def filename = 'data/test.txt'

def fieldDefs = [
empno  : 4, 
sal    : 3, 
deptno : 2
]


def pattern = "^" + fieldDefs.collect { k, v -> "(.{$v})" }.join('') + "\$"

rows = []
//new File(filename).eachLine { line ->
System.in.eachLine { line ->
    def m = line =~ pattern
    if (m) {
        def names = fieldDefs.keySet() as List
        def values = m[0][1..-1].collect { it.trim() }
        rows << [names, values].transpose().collectEntries{it}
    }
}

rows.each { row ->
    println '~' * 80
    row.each { k, v ->
        printf "%10s : %s\n", k, v
    }
}

