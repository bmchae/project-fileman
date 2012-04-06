while read line; do
	empno=${echo ${line:0:4}}
	sal=${echo ${line:5:8}}
	deptno=${echo ${line:10}}
    printf "%s %s %s\n", $deptno, $empno, $sal 
done < data/test.txt
