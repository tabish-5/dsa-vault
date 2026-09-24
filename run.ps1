Clear-Host
$user_input = Read-Host "Enter your commit message here"
git add .
git status
git commit -m "$user_input"
git push
