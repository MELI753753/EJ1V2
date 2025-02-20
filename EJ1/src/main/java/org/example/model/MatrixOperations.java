package org.example.model;

public class MatrixOperations {

    /**
     * Calcula la traza de la matriz representada en la QueueOfStacks (n pilas, cada una con n elementos).
     * Este método es destructivo: al finalizar, las pilas quedan modificadas.
     *
     * @param qOfStacks cola de pilas, dimensión n
     * @return la suma de los elementos de la diagonal principal
     */
    public static int trace(QueueOfStacks qOfStacks) {
        int n = qOfStacks.size(); // asumimos que la cola tiene n pilas
        int sum = 0;

        for (int i = 0; i < n; i++) {
            // Sacamos la pila del frente
            StaticStack st = qOfStacks.dequeue();

            // Quitamos i elementos del tope, para llegar al "diagonal"
            for (int k = 0; k < i; k++) {
                st.remove();  // desapilar
            }

            // Ahora el tope es el elemento de la diagonal
            sum += st.getTop();
            // Lo quitamos
            st.remove();

            // Volvemos a encolar la pila con los elementos restantes
            qOfStacks.enqueue(st);
        }
        return sum;
    }

    public static void printMatrix(QueueOfStacks qOfStacks, String label) {
        int n = qOfStacks.size();
        if (n == 0) {
            System.out.println(label + ": [Estructura vacía]");
            return;
        }
        // 1) leemos la matriz a un arreglo 2D (destruye la estructura internamente)
        int[][] mat = readMatrix(qOfStacks, n);

        // 2) la imprimimos
        System.out.println(label + ":");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(mat[i][j] + "\t");
            }
            System.out.println();
        }

        // 3) reconstruimos la QueueOfStacks original
        QueueOfStacks rebuilt = buildQueueOfStacks(mat);
        for (int i = 0; i < n; i++) {
            qOfStacks.enqueue(rebuilt.dequeue());
        }
    }

    /**
     * Genera y retorna la traspuesta de la matriz dada (QueueOfStacks).
     * Este método es destructivo respecto a la cola original (consume sus pilas).
     *
     * @param qOfStacks cola de pilas, dimensión n
     * @return otra QueueOfStacks que representa la traspuesta
     */
    public static QueueOfStacks transpose(QueueOfStacks qOfStacks) {
        int n = qOfStacks.size();  // número de pilas = dimensión n
        // 1) leer la matriz completa a un arreglo 2D
        int[][] mat = readMatrix(qOfStacks, n);

        // 2) transponer en un nuevo arreglo matT
        int[][] matT = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matT[j][i] = mat[i][j];
            }
        }

        // 3) construir la QueueOfStacks con matT y retornarla
        return buildQueueOfStacks(matT);
    }

    /**
     * Genera una matriz caracol (n x n) y la retorna en forma de QueueOfStacks.
     *
     * @param n dimensión
     * @return QueueOfStacks con la matriz caracol
     */
    public static QueueOfStacks snailMatrix(int n) {
        int[][] mat = new int[n][n];
        int val = 1;
        int top = 0, bottom = n - 1;
        int left = 0, right = n - 1;

        while (val <= n * n) {
            // 1) fila superior (left -> right)
            for (int j = left; j <= right; j++) {
                mat[top][j] = val++;
            }
            top++;

            // 2) columna derecha (top -> bottom)
            for (int i = top; i <= bottom; i++) {
                mat[i][right] = val++;
            }
            right--;

            // 3) fila inferior (right -> left)
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    mat[bottom][j] = val++;
                }
                bottom--;
            }

            // 4) columna izquierda (bottom -> top)
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    mat[i][left] = val++;
                }
                left++;
            }
        }

        // Construir la QueueOfStacks con esta matriz
        return buildQueueOfStacks(mat);
    }

    // ================== MÉTODOS PRIVADOS AUXILIARES ==================

    /**
     * Lee la matriz completa (n x n) desde qOfStacks, extrayendo todas sus pilas,
     * y retorna un arreglo 2D con los valores. Destruye la estructura original.
     *
     * @param qOfStacks cola de pilas
     * @param n dimensión
     * @return int[n][n] con los valores
     */
    private static int[][] readMatrix(QueueOfStacks qOfStacks, int n) {
        int[][] mat = new int[n][n];
        // Para cada fila i
        for (int i = 0; i < n; i++) {
            StaticStack st = qOfStacks.dequeue();
            // Extraemos la pila de tope a base => (col n-1 hasta col 0)
            for (int j = n - 1; j >= 0; j--) {
                mat[i][j] = st.getTop();
                st.remove();
            }
        }
        return mat;
    }

    /**
     * Construye una QueueOfStacks a partir de un arreglo 2D de enteros.
     * Cada fila del arreglo se convertirá en una pila (StaticStack) y se encolará.
     *
     * @param mat arreglo 2D
     * @return QueueOfStacks con la matriz
     */
    private static QueueOfStacks buildQueueOfStacks(int[][] mat) {
        int n = mat.length;
        QueueOfStacks qOfStacks = new QueueOfStacks(n);

        for (int i = 0; i < n; i++) {
            StaticStack st = new StaticStack();
            // Apilar mat[i][0], mat[i][1], ..., mat[i][n-1]
            for (int j = 0; j < n; j++) {
                st.add(mat[i][j]);
            }
            qOfStacks.enqueue(st);
        }
        return qOfStacks;
    }
}