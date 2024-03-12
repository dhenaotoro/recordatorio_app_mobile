# Tabla de Contenido

1. [Requisitos del Sistema](#requisitos-del-sistema)
2. [Alistar ambiente](#alistar-ambiente)
3. [Desplegar proyecto](#desplegar-proyecto)
4. [Usuario de pruebas](#usuario-de-pruebas)

## Requisitos del Sistema

Para utilizar esta aplicación, se deben cumplir los siguientes requisitos:

- **JDK**: Instalar la version de JDK 11 mediante los siguientes pasos:

**Para Windows**

1. Descargar el JDK de la siguiente ruta: [https://download.oracle.com/java/21/latest/jdk-21_windows-x64_bin.exe](https://www.oracle.com/co/java/technologies/javase/jdk11-archive-downloads.html#license-lightbox)
2. Dar dobleclick al archivo descargado y seguir todos los pasos por defecto de la instalación.

**Para Mac**

1. Instalar Homebrew:
```bash
/bin/bash -c “$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)”
```

2. Actualizar Homebrew:
```bash
  brew update
```

3. Instalar JDK 11:
```bash
  brew install java11
```

4.  Añadir java al PATH del sistema:
```bash
  sudo ln -sfn /usr/local/opt/openjdk@11/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-11.jdk
```

6.  Verificar que el JDK 11 se reconoce en el sistema:
```bash
  java --version
```

- **Android Studio**: Instalar Android Studio Iguana (Actualmente disponible [aquí](https://developer.android.com/studio)).


## Alistar ambiente

- Una vez ingresado al [repositorio](https://github.com/dhenaotoro/recordatorio_app_mobile) podemos clonar el repositorio al ejecutar alguno de los siguientes comandos
en un directorio de su máquina.

**HTTPS**: 
```bash
https://github.com/dhenaotoro/recordatorio_app_mobile.git
```

**SSH**: 
```bash
git@github.com:dhenaotoro/recordatorio_app_mobile.git
```

**GITHUB CLI**:
```bash
gh repo clone dhenaotoro/recordatorio_app_mobile
```

- Teniendo ya el repositorio clonado en la máquina, el siguiente paso es abrir Android Studio.

![Screenshot 2024-03-12 at 10 07 35 AM](https://github.com/dhenaotoro/recordatorio_app_mobile/assets/78186561/1e2d6323-90c7-49d0-8fe9-186df8e2ddfc)

- Teniendo abierto Android Studio, se procede a abrir el proyecto clonado dando clic sobre el botón `Abrir` o `Open`. Allí se selecciona el proyecto desde el sistema de archivos de su máquina:

![Screenshot 2024-03-12 at 10 10 39 AM](https://github.com/dhenaotoro/recordatorio_app_mobile/assets/78186561/984d9a3b-1ed6-4426-a55e-e13ab03c8190)

**Nota:** Por favor tener presente que el proyecto tarda 10 a 20 segundos en cargarse. El cual se debe visualizar similar a la siguiente imagen:

![Screenshot 2024-03-12 at 10 12 10 AM](https://github.com/dhenaotoro/recordatorio_app_mobile/assets/78186561/7ba3187e-6881-4317-9731-00ca4a570537)

Ya realizado los pasos el proyecto estara listo para desplegar.

## Desplegar proyecto

- Una vez teniendo el proyecto abierto en Android Studio se podrá lanzar una máquina de Android, en este caso del ejemplo se uso el dispositivo `Pixel_3a_API_34_extension_level7_arm64-v8a`. Al dar clic en el botón `Reproducir` de la barra
de ejecución ubicada en la esquina superior derecha.

![Screenshot 2024-03-12 at 10 19 29 AM](https://github.com/dhenaotoro/recordatorio_app_mobile/assets/78186561/29c2aff6-dbfa-40f8-b6b7-4d3a6ea0c0d8)

- La ejecución finalmente debe desplegar la pantalla del dispositivo `Pixel_3a_API_34_extension_level7_arm64-v8a` (El de su elección) al lado derecho (Sobre el icono de `Running Devices` de la sección de iconos del menú vertical derecho).
De esa manera se podrá interactuar con la aplicación móvil.

![Screenshot 2024-03-12 at 10 16 28 AM](https://github.com/dhenaotoro/recordatorio_app_mobile/assets/78186561/06cb4698-b582-412b-934d-55da80cd6a8c)

## Usuario de pruebas

No es necesario hacer uso de un usuario en especial para esta demostración. Se podrá usar cualquier usuario.

