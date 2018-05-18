set ANT_OPTS=-Dhibernate.id.generator.strategy=identity -Xms256M -Xmx512M

cd build-hbm
call ant -f  build-hbm.xml deploy
call removeunwantedfiles.bat