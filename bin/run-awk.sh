#cat data/emp.txt
#cat data/emp.txt | cut -d',' -f1,3,4 | sed 's/,//g' | tee data/test.txt

#cat data/test.txt | sed '/^0000$/d' | sed '/^ *$/d' | tee data/test.txt

head -5 data/test.txt | awk '
BEGIN {
}
{
    print "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
    print $0
    if (substr($1, 8, 2) == "10") {
        printf "\tempno  : %s\n",  substr($1, 0, 4);
        printf "\tsal    : %s\n",  substr($1, 5, 3);
        printf "\tdeptno : %s\n",  substr($1, 8, 2);
    }
}
'

