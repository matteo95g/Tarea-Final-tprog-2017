####################################################
#                                                  #
# EXTREAME COMPILATOR 3000                         #
# AUTHOR MATTEO GUERRIERI                          #
# SPECIAL THANKS TO STACK OVERFLOW AND MY FRIENDS  #
#                                                  #
####################################################

rm -r Compilados/*
echo "Compilando el Servidor Central..."
cd ServidorCentral
rm -r classes
mkdir classes
cd ..
javac -classpath "ServidorCentral/lib/*" ServidorCentral/src/*/*.java -d ServidorCentral/classes/
echo "Fin de la compilacion del Servidor Central"

sleep 1
echo "."
sleep 1
echo "."
sleep 1
echo "."

echo "Descomprimiendo librerias..."
cd ServidorCentral/classes
mkdir META-INF
rm -r META-INF
mkdir META-INF
cp ../../culturarte.properties META-INF/
jar -xf ../lib/*.jar
jar -cf ServidorCentral.jar *
mv ServidorCentral.jar ../../Compilados/
echo "ServidorCentral.jar creado con exito"

sleep 1
echo "."
sleep 1
echo "."
sleep 1
echo "."

cd ..
cd ..
echo "Compilando Estacion de Trabajo..."
cd EstacionDeTrabajo
rm -r classes
mkdir classes
cp persistence.xml classes/
mkdir classes/META-INF
cp persistence.xml classes/META-INF
javac -classpath "../Compilados/ServidorCentral.jar:lib/*" src/GUI/*.java -d classes
echo "Fin de la compilacion de la Estacion de Trabajo"

sleep 1
echo "."
sleep 1
echo "."
sleep 1
echo "."

echo "Generando la aplicacion ejecutable..."
echo Main-Class: GUI.Principal>MANIFEST.MF
cd classes
jar -xf ../lib/javax.persistence_2.1.0.v201304241213.jar
jar -xf ../lib/javax.persistence.source_2.1.0.v201304241213.jar
jar -xf ../lib/hsqldb.jar
jar -xf ../lib/hsqldb-2.4.0.jar
jar -xf ../lib/eclipselink.jar
jar -xf ../../Compilados/ServidorCentral.jar
jar -cmf ../MANIFEST.MF ../EstacionDeTrabajo.jar *
cd ..
cd ..
mv EstacionDeTrabajo/EstacionDeTrabajo.jar Compilados/

sleep 1
echo "."
sleep 1
echo "."
sleep 1
echo "."

echo "Creando CulturarteWeb.war"
sleep 2
cd CulturarteWeb
rm -r WebContent/WEB-INF/classes
mkdir WebContent/WEB-INF/classes
javac -classpath "WebContent/WEB-INF/lib/*" src/*/*.java -d WebContent/WEB-INF/classes/
cd WebContent
jar -cf CulturarteWeb.war *
mv CulturarteWeb.war ../../Compilados/
echo "CulturarteWeb creado con exito"
cd ..
cd ..

sleep 1
echo "."
sleep 1
echo "."
sleep 1
echo "."

echo "Creando CulturarteMovil.war"
sleep 2
cd CulturarteMovil
rm -r WebContent/WEB-INF/classes
mkdir WebContent/WEB-INF/classes
javac -classpath "WebContent/WEB-INF/lib/*" src/*/*.java -d WebContent/WEB-INF/classes/
cd WebContent
jar -cf CulturarteMovil.war *
mv CulturarteMovil.war ../../Compilados/
cd ..
cd ..
echo "CulturarteMovil creado con exito"

sleep 1
echo "."
sleep 1
echo "."
sleep 1
echo "."

sleep 2
echo "La aplicacion se ejecutara en 3 segundos"
sleep 1
echo "La aplicacion se ejecutara en 2 segundos"
sleep 1
echo "La aplicacion se ejecutara en 1 segundo"
sleep 1
java -jar Compilados/EstacionDeTrabajo.jar