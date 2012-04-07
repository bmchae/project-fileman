#!/usr/bin/env groovy

/**
 * Usage : cat data/test.txt | groovy run.groovy
 */
evaluate(new File("layout.groovy"))

/**
 * configuration
 */
def layout = [
empno  : 4, 
''     : 3, 
deptno : 2
]


//def filename = 'data/test.txt'


/**
 * program
 */
def pattern = "^" + layout.collect { k, v -> "(.{$v})" }.join('') + "\$"

rows = []
//new File(filename).eachLine { line ->
System.in.eachLine { line ->
    def m = line =~ pattern
    if (m) {
        def names = layout.keySet() as List
        def values = m[0][1..-1].collect { it.trim() }
        row = [names, values].transpose().collectEntries{it} 

        println '~' * 80
        println line
        println row
        row.each { k, v ->
            if (!(k =~ /^ *$/))
                printf "%10s : %s\n", k, v
        }
    } else {
        println 'not match : ' + line
    }
}

