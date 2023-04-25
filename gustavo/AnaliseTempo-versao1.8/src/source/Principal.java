package source;


public class Principal {
	
	// Method to build an array of integers in descending order
	public int[] constroiLista(int tamanho) {
		int lista[] = new int[tamanho];
		for(int x=tamanho,y=0;x>0;x--,y++) {
			lista[y]=x;
		}
		return lista;
	}
	
	// Method to print a long array
	public void imprimeLista(long[] lista) {
		for(int x=0;x<lista.length;x++) {
			System.out.print(lista[x]+" ");
		}
		System.out.println();
	}
	
	// Method to print an int array
	public void imprimeLista(int[] lista) {
		for(int x=0;x<lista.length;x++) {
			System.out.print(lista[x]+" ");
		}
		System.out.println();
	}
	
	// Method to print a double array
	private int[][] constroiMatriz(int x) {
		int matriz[][] = new int[x][x];
		for(int i=0;i<x;i++) {
			for(int j=0;j<x;j++) {
				matriz[i][j]=i+j;
			}
		}
		return matriz;
	}

	//method to construct vector
	private int[] constroiVetor(int x) {
		int vetor[] = new int[x];
		for(int i=0;i<x;i++) {
			vetor[i]=i;
		}
		return vetor;
	}

	
	void bubbleSort(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j+1] and arr[j]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }

	void insertionSort(int arr[]) {
		int n = arr.length;
		for (int i = 1; i < n; ++i) {
			int key = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j = j - 1;
			}
			arr[j + 1] = key;
		}
	}


// Shell sort function that takes an int array and returns void
void shellSort(int[] arr) {
  // Find the gap size by dividing the array length by 2
  int gap = arr.length / 2;
  // Loop until the gap is 0
  while (gap > 0) {
    // Loop through the array from the gap to the end
    for (int i = gap; i < arr.length; i++) {
      // Store the current element
      int temp = arr[i];
      // Initialize a pointer to the previous element with the same gap
      int j = i - gap;
      // Loop backwards until the pointer is negative or the element is smaller than temp
      while (j >= 0 && arr[j] > temp) {
        // Shift the element to the right by the gap
        arr[j + gap] = arr[j];
        // Move the pointer to the left by the gap
        j -= gap;
      }
      // Insert temp at the correct position
      arr[j + gap] = temp;
    }
    // Reduce the gap size by half
    gap /= 2;
  }
}

	void cubeSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}
	//function that multiplies a vector by a vector
	void multiply(int vetor[], int vetor2[]) {
		int C[] = new int[vetor.length];
		for (int i = 0; i < vetor.length; i++) {
			C[i] = vetor[i] * vetor2[i];
		}
	}



	//function that multiplies a square matrix by a vector
	void multiply(int A[][], int vetor[]) {
		int C[] = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				C[i] += A[i][j] * vetor[j];
			}
		}
	}
	//funtion that multiplies two square matrices
	void multiply(int A[][], int B[][]) {
		//check if the number of columns of the first matrix is equal to the number of rows of the second matrix
		if (A[0].length != B.length) {
			System.out.println("Matrizes não compatíveis");
			return;
		}
		//create a new matrix that will contain the result of the multiplication
		int C[][] = new int[A.length][B[0].length];
		//loop through the rows of the first matrix
		for (int i = 0; i < A.length; i++) {
			//loop through the columns of the second matrix
			for (int j = 0; j < B[0].length; j++) {
				//loop through the rows of the second matrix
				for (int k = 0; k < B.length; k++) {
					//add the product of the current row of the first matrix and the current column of the second matrix to the result matrix
					C[i][j] += A[i][k] * B[k][j];
				}
			}
		}
	}

	//function that executes multiplication of a vector by a vector by calling multiply function 1000 times
	public long[] executeV() {
		Principal programa = new Principal();
		long tempos[] = new long[500];
		for(int x=1;x<=500;x++) {
			int l[] = programa.constroiVetor(x);
			int l2[] = programa.constroiVetor(x);
			long ini = System.nanoTime();
			programa.multiply(l, l2);
			long fim = System.nanoTime();
			tempos[x-1]=fim-ini;
		}
		return tempos;
	}


//function that executes multiplication of two square matrices by calling multiply function 1000 times
 public long[] executeM() {
	Principal programa = new Principal();
	long tempos[] = new long[500];
	for(int x=1;x<=500;x++) { 
		int l[][] = programa.constroiMatriz(x);
		int l2[][] = programa.constroiMatriz(x);
		long ini = System.nanoTime(); 
		programa.multiply(l, l2); 
		long fim = System.nanoTime(); 
		tempos[x-1]=fim-ini; } 
		return tempos; 
	}


//function that executes multiplication of two square matrices by calling multiply function 1000 times using threads
public long[] executeMThreads() {
  Principal programa = new Principal();
  long tempos[] = new long[1000];
  //create an array of threads
  Thread[] threads = new Thread[1000];
  for(int x=1;x<=1000;x++) {
	int l[][] = programa.constroiMatriz(x);
	int l2[][] = programa.constroiMatriz(x);
	//declare a final variable and assign it the value of x
	final int index = x;
	Runnable task = new Runnable() {
	  @Override
	  public void run() {
		long ini = System.nanoTime();
		programa.multiply(l, l2);
		long fim = System.nanoTime();
		//use the final variable instead of x
		tempos[index-1]=fim-ini;
	  }
	};
	threads[x-1] = new Thread(task);
	threads[x-1].start();
  }
  //wait for all threads to finish
  for(int x=0;x<1000;x++) {
    try {
      threads[x].join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  return tempos;
}
	//function that executes multiplication of a square matrix by a vector by calling multiply function 1000 times
	public long[] executeMV() {
		Principal programa = new Principal();
		long tempos[] = new long[500];
		for(int x=1;x<=500;x++) {
			int l[][] = programa.constroiMatriz(x);			
			int vetor[] = programa.constroiVetor(x);		
			long ini = System.nanoTime();
			programa.multiply(l, vetor);
			long fim = System.nanoTime();
			tempos[x-1]=fim-ini;
		}
		return tempos;
	}

	

	public long[] execute() {
		Principal programa = new Principal();
		long tempos[] = new long[1000];
		for(int x=1;x<=1000;x++) {
			int l[] = programa.constroiLista(x);		
			long ini = System.nanoTime();
			programa.bubbleSort(l);
			long fim = System.nanoTime();
			tempos[x-1]=fim-ini;
		}
		//programa.imprimeLista(tempos);
		return tempos;
	}

public long[] executeA() {
	Principal programa = new Principal();
	long tempos[] = new long[1000];
	for(int x=1;x<=1000;x++) {
		int l[] = programa.constroiLista(x);		
		long ini = System.nanoTime();
		programa.insertionSort(l);
		long fim = System.nanoTime();
		tempos[x-1]=fim-ini;
	}
	//programa.imprimeLista(tempos);
	return tempos;
}

public long[] executeB() {
	Principal programa = new Principal();
	long tempos[] = new long[1000];
	for(int x=1;x<=1000;x++) {
		int l[] = programa.constroiLista(x);		
		long ini = System.nanoTime();
		programa.cubeSort(l);
		long fim = System.nanoTime();
		tempos[x-1]=fim-ini;
	}
	//programa.imprimeLista(tempos);
	return tempos;
}

public long[] executeC() {
	Principal programa = new Principal();
	long tempos[] = new long[1000];
	for(int x=1;x<=1000;x++) {
		int l[] = programa.constroiLista(x);		
		long ini = System.nanoTime();
		programa.shellSort(l);
		long fim = System.nanoTime();
		tempos[x-1]=fim-ini;
	}
	//programa.imprimeLista(tempos);
	return tempos;
}

}

