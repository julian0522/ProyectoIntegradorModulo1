
# 📚 Sistema de Registro Estudiantil en Consola

Este es un sistema básico en Java orientado a consola que permite **registrar**, **consultar**, **eliminar** y **calcular el promedio** de notas de un estudiante a la vez.

## 🧠 Funcionalidades principales

- Registrar un estudiante con 3 notas.
- Validar los datos ingresados.
- Confirmar o cancelar el registro.
- Mostrar los datos del estudiante actual.
- Calcular el promedio de notas y determinar si aprobó.
- Eliminar al estudiante actual.
- Navegar mediante un menú interactivo.

---

## ⚙️ Estructura del Proyecto

### 📁 Clase principal: `App`

Contiene toda la lógica del sistema. Se estructura en métodos estáticos para:

- **Interacción con el usuario** mediante consola (`Scanner`).
- **Control del flujo** a través de un menú principal (`menu()`).
- **Gestión del estado del estudiante registrado** (nombre y notas).

### 📌 Variables globales

```java
static String nombre;
static double nota1;
static double nota2;
static double nota3;
static Scanner scanner = new Scanner(System.in);
```

Estas variables mantienen el estado del estudiante registrado mientras el programa se ejecuta.

---

## 📋 Menú principal

El método `menu()` despliega las siguientes opciones:

```
1. Registrar Datos de un Estudiante
2. Mostrar datos del estudiante actual
3. Calcular promedio de notas del estudiante actual
4. Borrar informacion estudiante actual
0. Salir
```

---

## 🧾 Funciones clave

### `registrarEstudiante()`

Permite ingresar el nombre y las 3 notas del estudiante, validando:

- El nombre no debe contener números ni caracteres especiales.
- Las notas deben estar entre 0 y 5.

Incluye una confirmación antes de guardar los datos.

---

### `validarFormulario(...)`

Valida el nombre y las notas ingresadas con reglas básicas y una expresión regular para el nombre.

---

### `confirmacionRegistro()`

Muestra un resumen de los datos ingresados y solicita confirmación del usuario (1 para confirmar, 0 para cancelar).

---

### `mostrarDatosEstudiante()`

Muestra los datos del estudiante actual si hay uno registrado.

---

### `calcularPromedioNotas()`

Calcula el promedio de las tres notas y solicita al usuario la nota mínima aprobatoria para determinar si el estudiante aprobó o no.

---

### `borrarEstudiante()`

Elimina los datos del estudiante actual y reinicia las variables.

---

## 🛑 Consideraciones

- Solo se gestiona **un estudiante a la vez**.
- No se usa persistencia de datos (todo se mantiene en memoria mientras la aplicación se ejecuta).
- Se usan estructuras básicas de control y entrada/salida por consola.

