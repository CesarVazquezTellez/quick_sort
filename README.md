QuickSort es uno de los algoritmos de ordenamiento más eficientes y ampliamente utilizados. Funciona mediante el paradigma "divide y vencerás", seleccionando un elemento como pivote y particionando el arreglo alrededor de él.
Características Importantes:

Complejidad temporal:

Mejor caso: O(n log n)
Caso promedio: O(n log n)
Peor caso: O(n²) - cuando el pivote siempre es el menor o mayor elemento


Complejidad espacial: O(log n) - debido a la pila de recursión
Tipo de algoritmo: Divide y vencerás
Ordenamiento: In-place (no requiere arreglo auxiliar)
Estabilidad: No es estable (puede cambiar el orden relativo de elementos iguales)
Método: Comparación

Ventajas:

Muy rápido en la práctica para la mayoría de datos
Usa poca memoria adicional
Buen rendimiento en caché (localidad de datos)
Divide el problema en subproblemas más pequeños

Desventajas:

Rendimiento O(n²) en el peor caso
Implementación recursiva puede causar stack overflow con datos grandes
No es estable


Funcionamiento del Código
Este programa implementa QuickSort clásico con el esquema de partición de Lomuto:
1. Lectura de datos (readNumbersFromFile)

Lee números desde un archivo de texto
Permite múltiples números por línea separados por espacios
Convierte la lista a un arreglo de enteros

2. Algoritmo QuickSort (quickSort)
java   public static void quickSort(int[] arr, int low, int high)
```
   - **Caso base**: Si `low >= high`, el subarreglo tiene 0 o 1 elemento (ya está ordenado)
   - **Particionamiento**: Llama a `partition()` para colocar el pivote en su posición correcta
   - **Recursión**:
     - Ordena la parte izquierda: `quickSort(arr, low, pivotIndex - 1)`
     - Ordena la parte derecha: `quickSort(arr, pivotIndex + 1, high)`

### 3. **Función de partición** (`partition`)
   - Selecciona el **último elemento** como pivote: `pivot = arr[high]`
   - Variable `i` rastrea la posición del último elemento menor o igual al pivote
   - Recorre el arreglo con `j`:
     - Si `arr[j] <= pivot`: intercambia `arr[i+1]` con `arr[j]`, incrementa `i`
   - Al final, coloca el pivote en su posición correcta: `swap(arr, i + 1, high)`
   - Retorna la posición final del pivote

### 4. **Función auxiliar** (`swap`)
   - Intercambia dos elementos en el arreglo

### 5. **Escritura de resultados** (`writeNumbersToFile`)
   - Escribe cada número ordenado en una línea separada

---

## Prueba de Escritorio (Ejemplo Manual)

### Datos de entrada:
**Archivo de entrada**: `datos.txt`
```
38 27 43 3 9 82 10
```

### Ejecución paso a paso:

**Arreglo inicial**:
```
[38, 27, 43, 3, 9, 82, 10]
índices: 0   1   2   3  4   5   6
```

---

#### **LLAMADA 1**: `quickSort(arr, 0, 6)`

**Partición 1**: `partition(arr, 0, 6)`
- Pivote = `arr[6] = 10`
- `i = -1` (antes del inicio)

| j | arr[j] | Comparación | Acción | Arreglo |
|---|--------|-------------|--------|---------|
| 0 | 38 | 38 > 10 | No swap | [38, 27, 43, 3, 9, 82, 10] |
| 1 | 27 | 27 > 10 | No swap | [38, 27, 43, 3, 9, 82, 10] |
| 2 | 43 | 43 > 10 | No swap | [38, 27, 43, 3, 9, 82, 10] |
| 3 | 3 | 3 ≤ 10 | i=0, swap(0,3) | [**3**, 27, 43, **38**, 9, 82, 10] |
| 4 | 9 | 9 ≤ 10 | i=1, swap(1,4) | [3, **9**, 43, 38, **27**, 82, 10] |
| 5 | 82 | 82 > 10 | No swap | [3, 9, 43, 38, 27, 82, 10] |

- Colocar pivote: `swap(i+1, high)` → `swap(2, 6)`

**Resultado partición**:
```
[3, 9, 10, 38, 27, 82, 43]
        ↑ pivote en posición 2
```

**Recursión**:
- Izquierda: `quickSort(arr, 0, 1)` → ordena [3, 9]
- Derecha: `quickSort(arr, 3, 6)` → ordena [38, 27, 82, 43]

---

#### **LLAMADA 2**: `quickSort(arr, 0, 1)` - Subarreglo [3, 9]

**Partición 2**: `partition(arr, 0, 1)`
- Pivote = `arr[1] = 9`
- `i = -1`

| j | arr[j] | Comparación | Acción                      |
|---|--------|-------------|-----------------------------|
| 0 | 3      | 3 ≤ 9       | i=0, swap(0,0) - sin cambio |

- Colocar pivote: `swap(1, 1)` - sin cambio

**Resultado**: `[3, 9]` - ya ordenado

---

#### **LLAMADA 3**: `quickSort(arr, 3, 6)` - Subarreglo [38, 27, 82, 43]

**Partición 3**: `partition(arr, 3, 6)`
- Pivote = `arr[6] = 43`
- `i = 2` (antes del subarreglo)

| j | arr[j] | Comparación | Acción | Arreglo |
|---|--------|-------------|--------|---------|
| 3 | 38 | 38 ≤ 43 | i=3, swap(3,3) | [3, 9, 10, 38, 27, 82, 43] |
| 4 | 27 | 27 ≤ 43 | i=4, swap(4,4) | [3, 9, 10, 38, 27, 82, 43] |
| 5 | 82 | 82 > 43 | No swap        | [3, 9, 10, 38, 27, 82, 43] |

- Colocar pivote: `swap(5, 6)`

**Resultado**:
```
[3, 9, 10, 38, 27, 43, 82]
                    ↑ pivote en posición 5
```

**Recursión**:
- Izquierda: `quickSort(arr, 3, 4)` → ordena [38, 27]
- Derecha: `quickSort(arr, 6, 6)` → [82] ya ordenado

---

#### **LLAMADA 4**: `quickSort(arr, 3, 4)` - Subarreglo [38, 27]

**Partición 4**: `partition(arr, 3, 4)`
- Pivote = `arr[4] = 27`
- `i = 2`

| j | arr[j] | Comparación | Acción |
|---|--------|-------------|--------|
| 3 | 38 | 38 > 27 | No swap |

- Colocar pivote: `swap(3, 4)`

**Resultado**:
```
[3, 9, 10, 27, 38, 43, 82]
```

---

### Resultado Final:

**Archivo de salida**: `resultado.txt`
```
3
9
10
27
38
43
82
```

---

## Visualización del Árbol de Recursión
```
                    [38,27,43,3,9,82,10]
                            ↓
                    pivote=10, pos=2
                   /                  \
            [3,9]                    [38,27,82,43]
              ↓                            ↓
         pivote=9                    pivote=43, pos=5
         pos=1                      /              \
        /     \              [38,27]              [82]
    [3]      [9]               ↓                   ↓
     ↓        ↓           pivote=27             ordenado
  ordenado ordenado       pos=3
                         /      \
                     [27]      [38]
                       ↓        ↓
                   ordenado  ordenado