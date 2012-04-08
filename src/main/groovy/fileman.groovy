#!/usr/bin/env groovy

/**
 * Usage : cat data/test.txt | groovy run.groovy
 */
//evaluate(new File("layout.groovy"))

/**
 * configuration
 */
layout = [
    empno  : ',',
    ename  : ',',
    deptno : [
             sal    : 3,
             deptno : 2
             ]
]

/**
 * program
 */
rows = []
//new File(filename).eachLine { line ->
System.in.eachLine { line ->
    println '~' * 80
    println line
    def names = layout.keySet() as List
	def values = line.split(',').collect { it.trim() }
    row = [names, values].transpose().collectEntries{it} 
	println row
    row.each { k, v ->
		if (k == 'deptno')
		    parsePart(layout[k], v)
	}
}

/**
 * parse part
 */
def parsePart(layout, part) {
    pattern = "^" + layout.collect { k, v -> "(.{$v})" }.join('') + "\$"

    def m = part =~ pattern
    if (m) {
        def names = layout.keySet() as List
        def values = m[0][1..-1].collect { it.trim() }
        row = [names, values].transpose().collectEntries{it} 
		println row
        row.each { k, v ->
            if (!(k =~ /^\-? *$/))
                printf "%10s : %s\n", k, v
        }
    } else {
        println 'not match : ' + part
    }
}

