Add-Type -AssemblyName System.Windows.Forms

$username = "kalevo.wsl"

# Ask for password without displaying it
# $securePassword = Read-Host "Instagram password" -AsSecureString
# $password = [Runtime.InteropServices.Marshal]::PtrToStringAuto(
#     [Runtime.InteropServices.Marshal]::SecureStringToBSTR($securePassword)
# )

$password = 'In$ta08120410'


# Open Chrome Incognito
Start-Process "chrome.exe" -ArgumentList '--incognito "https://www.instagram.com/accounts/login/"'

# Give Chrome time to load
Start-Sleep -Seconds 7

# Type username
[System.Windows.Forms.SendKeys]::SendWait($username)

# Move to password field
[System.Windows.Forms.SendKeys]::SendWait("{TAB}")

# Type password
[System.Windows.Forms.SendKeys]::SendWait($password)

# Submit
[System.Windows.Forms.SendKeys]::SendWait("{ENTER}")

# Remove password from the PowerShell variable
$password = $null

# Give Chrome time to load
Start-Sleep -Seconds 17

# Open Gmail in Google Chrome
Start-Process "chrome.exe" "https://mail.google.com/mail/u/0/#inbox"

# Wait for Gmail to load
Start-Sleep -Seconds 5

# Open the most recent email
Start-Process "chrome.exe" "https://mail.google.com/mail/u/0/#inbox"
