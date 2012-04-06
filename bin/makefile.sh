while read LINE; do
  echo $LINE | cut -c5- >> $(echo $LINE | cut -c8-10).txt
done < data/test.txt
