@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem
@rem SPDX-License-Identifier: Apache-2.0
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  intelligent-scheduler startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
@rem This is normally unused
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and INTELLIGENT_SCHEDULER_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS="--module-path" "C:\Users\casun\.gradle\caches\modules-2\files-2.1\org.openjfx\javafx-fxml\17.0.2\5079f88d5345e988222c90a345ad682b7aaccad3\javafx-fxml-17.0.2-win.jar;C:\Users\casun\.gradle\caches\modules-2\files-2.1\org.openjfx\javafx-controls\17.0.2\707f290bacde2738c0a7e1d0b4a8193002c29cf7\javafx-controls-17.0.2-win.jar;C:\Users\casun\.gradle\caches\modules-2\files-2.1\org.openjfx\javafx-controls\17.0.2\f52640ea07a0d7fe2ac8b9f945ec58fa22ac1afd\javafx-controls-17.0.2.jar;C:\Users\casun\.gradle\caches\modules-2\files-2.1\org.openjfx\javafx-graphics\17.0.2\6f95886c8fed3e1b21370a199c3937846ef6b3cc\javafx-graphics-17.0.2-win.jar;C:\Users\casun\.gradle\caches\modules-2\files-2.1\org.openjfx\javafx-graphics\17.0.2\1eb7ce07e07ccc2fcb26103f07535e72069519d6\javafx-graphics-17.0.2.jar;C:\Users\casun\.gradle\caches\modules-2\files-2.1\org.openjfx\javafx-base\17.0.2\1bd6dc88b180a6239a5067320c2f0d7f3526e1d2\javafx-base-17.0.2-win.jar;C:\Users\casun\.gradle\caches\modules-2\files-2.1\org.openjfx\javafx-base\17.0.2\ca46f15a306175ac1513211e1d190a5baacd77cc\javafx-base-17.0.2.jar" "--add-modules" "javafx.base,javafx.graphics,javafx.controls,javafx.fxml"

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

echo. 1>&2
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH. 1>&2
echo. 1>&2
echo Please set the JAVA_HOME variable in your environment to match the 1>&2
echo location of your Java installation. 1>&2

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo. 1>&2
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME% 1>&2
echo. 1>&2
echo Please set the JAVA_HOME variable in your environment to match the 1>&2
echo location of your Java installation. 1>&2

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\intelligent-scheduler-1.0-SNAPSHOT.jar;%APP_HOME%\lib\javafx-fxml-17.0.2-win.jar;%APP_HOME%\lib\javafx-controls-17.0.2-win.jar;%APP_HOME%\lib\javafx-controls-17.0.2.jar;%APP_HOME%\lib\javafx-graphics-17.0.2-win.jar;%APP_HOME%\lib\javafx-graphics-17.0.2.jar;%APP_HOME%\lib\javafx-base-17.0.2-win.jar;%APP_HOME%\lib\javafx-base-17.0.2.jar


@rem Execute intelligent-scheduler
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %INTELLIGENT_SCHEDULER_OPTS%  -classpath "%CLASSPATH%" main.java.com.scheduler.MainApp %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem Set variable INTELLIGENT_SCHEDULER_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
if not ""=="%INTELLIGENT_SCHEDULER_EXIT_CONSOLE%" exit %EXIT_CODE%
exit /b %EXIT_CODE%

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
