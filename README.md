# AcademiaApp - Sistema de Gestión Académica

![Android](https://img.shields.io/badge/Platform-Android-green.svg)
![Java](https://img.shields.io/badge/Language-Java-orange.svg)
![Room](https://img.shields.io/badge/Database-Room-blue.svg)
![Material3](https://img.shields.io/badge/UI-Material3-purple.svg)

## 📱 Descripción

AcademiaApp es una aplicación Android desarrollada en Java que permite gestionar información académica de manera eficiente. El sistema implementa operaciones CRUD completas sobre una base de datos local utilizando Room (SQLite), siguiendo las mejores prácticas de arquitectura Android.

## ✨ Funcionalidades Principales

### Gestión de Estudiantes
- ➕ Agregar nuevos estudiantes con información completa
- 📋 Listar todos los estudiantes registrados
- ✏️ Editar información de estudiantes existentes
- 🗑️ Eliminar estudiantes del sistema
- 📧 Almacenamiento de datos de contacto (email, teléfono)

### Gestión de Materias
- ➕ Crear nuevas materias académicas
- 📋 Visualizar catálogo completo de materias
- ✏️ Actualizar información de materias
- 🗑️ Eliminar materias del sistema
- 📊 Gestión de créditos y códigos de materia

### Sistema de Inscripciones
- 🔗 Relación entre estudiantes y materias
- 📅 Registro de fechas de inscripción
- ✅ Control de estado (Activo, Finalizado, Retirado)

### Registro de Calificaciones
- 📝 Registro de evaluaciones por inscripción
- 🎯 Tipos de evaluación (Parcial, Final, Trabajo, etc.)
- 💯 Almacenamiento de notas y observaciones

## 🏗️ Arquitectura y Tecnologías

### Base de Datos
- **Room Database** (SQLite)
- 4 tablas relacionadas con claves foráneas
- Relaciones 1:N y N:M
- Operaciones asíncronas con ExecutorService

### Patrón de Arquitectura
- **MVVM** (Model-View-ViewModel)
- Repository Pattern
- LiveData para observación de datos
- ViewBinding en todas las pantallas

### Componentes Android
- ✅ **Navigation Component** - Navegación entre pantallas
- ✅ **Bottom Navigation** - Menú de navegación inferior
- ✅ **Toolbar personalizado** - Barra superior con títulos dinámicos
- ✅ **RecyclerView** - Listas eficientes y optimizadas
- ✅ **Material Design 3** - Interfaz moderna y atractiva
- ✅ **ViewBinding** - Acceso seguro a vistas
- ✅ **AlertDialog** - Diálogos para CRUD y confirmaciones

## 📦 Estructura del Proyecto

```
com.example.academiaapp/
│
├── database/              # Entidades Room y DAOs
│   ├── Estudiante.java
│   ├── Materia.java
│   ├── Inscripcion.java
│   ├── Calificacion.java
│   ├── EstudianteDao.java
│   ├── MateriaDao.java
│   ├── InscripcionDao.java
│   ├── CalificacionDao.java
│   └── AppDatabase.java
│
├── repository/            # Capa de datos
│   ├── EstudianteRepository.java
│   └── MateriaRepository.java
│
├── ui/                    # Fragments y ViewModels
│   ├── HomeFragment.java
│   ├── EstudiantesFragment.java
│   ├── MateriasFragment.java
│   ├── EstudianteViewModel.java
│   └── MateriaViewModel.java
│
├── adapters/              # Adapters para RecyclerView
│   ├── EstudianteAdapter.java
│   └── MateriaAdapter.java
│
└── MainActivity.java      # Activity principal
```

## 🗄️ Modelo de Base de Datos

### Tabla: estudiantes
```sql
- id (PK, AutoIncrement)
- nombre (TEXT)
- apellido (TEXT)
- email (TEXT)
- telefono (TEXT)
```

### Tabla: materias
```sql
- id (PK, AutoIncrement)
- nombre (TEXT)
- codigo (TEXT)
- creditos (INTEGER)
- descripcion (TEXT)
```

### Tabla: inscripciones
```sql
- id (PK, AutoIncrement)
- estudianteId (FK -> estudiantes)
- materiaId (FK -> materias)
- fecha (TEXT)
- estado (TEXT)
```

### Tabla: calificaciones
```sql
- id (PK, AutoIncrement)
- inscripcionId (FK -> inscripciones)
- tipoEvaluacion (TEXT)
- nota (REAL)
- fecha (TEXT)
- observaciones (TEXT)
```

## 🚀 Instalación y Configuración

### Requisitos Previos
- Android Studio (última versión)
- JDK 8 o superior
- Android SDK API 24 o superior
- Dispositivo Android o Emulador

### Pasos de Instalación

1. **Clonar el repositorio**
```bash
git clone https://github.com/tu-usuario/programacion2-primerparcial.git
cd programacion2-primerparcial
```

2. **Abrir en Android Studio**
   - File → Open
   - Seleccionar la carpeta del proyecto
   - Esperar sincronización de Gradle

3. **Compilar el proyecto**
   - Build → Make Project (Ctrl+F9)

4. **Ejecutar la aplicación**
   - Run → Run 'app' (Shift+F10)
   - Seleccionar dispositivo/emulador


## 🧪 Pruebas Realizadas

- ✅ CRUD completo de Estudiantes
- ✅ CRUD completo de Materias
- ✅ Validación de campos obligatorios
- ✅ Validación de formatos de datos
- ✅ Persistencia de datos local
- ✅ Navegación entre pantallas
- ✅ Funcionalidad de RecyclerView
- ✅ Relaciones de base de datos (Foreign Keys)
- ✅ Eliminación en cascada

## 📋 Requisitos Cumplidos

| # | Requisito | Estado | Puntos |
|---|-----------|--------|--------|
| 1 | 4 tablas relacionadas (1:N o N:M) | ✅ | 2P |
| 2 | CRUD completo en 2 tablas mínimo | ✅ | 3P |
| 3 | Room con anotaciones (@Entity, @Dao, @Database) | ✅ | 1P |
| 4 | ViewBinding en todas las pantallas | ✅ | 1P |
| 5 | RecyclerView con Adapter | ✅ | 1P |
| 6 | Icono personalizado | ✅ | 1P |
| 7 | BottomNavigation y Toolbar | ✅ | 1P |
| 8 | Navigation Graph | ✅ | 1P |
| 9 | Documentación de pruebas | ✅ | 1P |
| 10 | Repositorio GitHub con README | ✅ | 3P |


## 👨‍💻 Autor

Jose Ariel Arce Aquino
- GitHub: (https://github.com/WannaCry-zip/programacion2-primerparcial.git)
- Email: arcejosepsn@gmail.com

## 📄 Licencia

Este proyecto fue desarrollado como parte del Primer Parcial de Programación II.
---

⭐ Si te gusta este proyecto, ¡dale una estrella en GitHub!
