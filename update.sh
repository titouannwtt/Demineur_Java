git add *
if [ $# -eq 1 ]
then
git commit -m $1
else
git commit -m "no message"
fi
git push
