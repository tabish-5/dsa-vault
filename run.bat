@cls
@set /p user_input="Enter your commit message here: "
@git add .
@git status
@git commit -m "%user_input%"
@git pull
@git push
@cls