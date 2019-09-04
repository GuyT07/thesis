git branch "%1"
git checkout "%1"
git add resources/**
git add ecj-archive/**
git add output/**
git add ecj-simplified/**

xcopy %USERPROFILE%\IdeaProjects\thesis\ecj\evolution.properties %USERPROFILE%\Desktop\testar\evolution.properties /Y
xcopy %USERPROFILE%\IdeaProjects\thesis\ecj\src\main\resources\ec\app\testar\%2 %USERPROFILE%\Desktop\testar\%2 /Y
xcopy %USERPROFILE%\IdeaProjects\thesis\ecj\out.stat %USERPROFILE%\Desktop\testar\out.stat /Y
xcopy %USERPROFILE%\IdeaProjects\thesis\ecj\meta.stat %USERPROFILE%\Desktop\testar\meta.stat /Y
xcopy %USERPROFILE%\IdeaProjects\thesis\ecj\front.stat %USERPROFILE%\Desktop\testar\front.stat /Y

git add evolution.properties
git add %2
git add *.stat

git commit -m "Automatic commit on branch %1, params used: %2"

git push --set-upstream origin %1

git push