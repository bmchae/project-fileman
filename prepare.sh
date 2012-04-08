#cat data/emp.txt
#cat data/emp.txt | cut -d',' -f1,2,3,4 | sed 's/,//g' | tee data/test.txt
cat data/emp.txt  | awk -F ',' '{printf "%s,%s,%s%s\n", $1,$2,$3,$4}' > data/test.txt
#cat data/emp.txt | awk '{print $1 $2 $3 $4}' | tee data/test.txt
cat data/test.txt | sed '/^0000/d' | sed '/^ *$/d' | tee data/test.txt

