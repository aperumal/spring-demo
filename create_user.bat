@echo off
REM Create new user in H2 database via REST API
setlocal enabledelayedexpansion

set "url=http://localhost:8080/api/users"
set "json={\"username\":\"newuser001\",\"email\":\"newuser001@example.com\",\"password\":\"SecurePass123\",\"fullName\":\"New User One\",\"phoneNumber\":\"555-0001\",\"isActive\":true}"

powershell -Command "Invoke-WebRequest -Uri '%url%' -Method POST -ContentType 'application/json' -Body '%json%' -UseBasicParsing | Select-Object -ExpandProperty Content"
