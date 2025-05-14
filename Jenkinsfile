stage('Pruebas Unitarias') {
    steps {
        sh 'javac -cp .:junit-4.13.2.jar org/tu/paquete/ProveedorDaoTest.java'
        sh 'java -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore Modelo.ProveedorDaoTest'
    }
}
