// clean resources/output
for /d %%G in ("resources\output\scrshots\\*") do rd /s /q "%%~G"
del /Q /F resources\output\sequences\\*
del /Q /F resources\output\sequences_ok\\*
del /Q /F resources\output\sequences_unexpectedclose\\*
del /Q /F resources\output\temp\\*