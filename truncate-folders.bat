// clean resources/output
del /Q /F resources\output\*.log
del /Q /F resources\output\logs\\*.log
del /Q /F resources\output\metrics\\*.csv
for /d %%G in ("resources\output\scrshots\\*") do rd /s /q "%%~G"
for /d %%G in ("resources\output\sequences*") do rd /s /q "%%~G"
del /Q /F resources\output\temp\\*

// clean root
del /Q /F archived\*.log
del /Q /F ecj-simplified\*.csv

del /Q /F out.stat

del /Q /F output\logs\*.log