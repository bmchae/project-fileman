#!/usr/bin/env groovy

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


//def filename = 'data/test.txt'


/**
 * program
 */
def pattern = "^" + fieldDefs.collect { k, v -> "(.{$v})" }.join('') + "\$"

rows = []
//new File(filename).eachLine { line ->
System.in.eachLine { line ->
    def m = line =~ pattern
    if (m) {
        def names = fieldDefs.keySet() as List
        def values = m[0][1..-1].collect { it.trim() }
        rows << [names, values].transpose().collectEntries{it}
    } else {
        println 'not match : ' + line
    }
}

rows.each { row ->
    println '~' * 80
    row.each { k, v ->
        if (!(k =~ /^ *$/))
            printf "%10s : %s\n", k, v
    }
}

