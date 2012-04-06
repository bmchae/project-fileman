while read line; do
    #echo $line | cut -c6- >> $(echo $line | cut -c8-10).txt
    empno=$(echo $line | cut -c1-4)
    sal=$(echo $line | cut -c5-7)
    deptno=$(echo $line | cut -c8-)
    printf "%s %s %s\n", $deptno, $empno, $sal 
done < data/test.txt
