# -*- coding: utf-8 -*-
#importa funcao que gera valores inteiros aleatorios
from random import randint 
import time
import matplotlib.pyplot as plt

'''
lista1 e lista2 precisam ter o mesmo tamanho
retorna a lista com a soma em cada posicao
'''

def somaListas(lista1,lista2):
    tamanho = len(lista1)
    resultante = []
    for i in range(tamanho):
        resultante.append(lista1[i]+lista2[i])
    return resultante

def multListaMatriz(lista,matriz):
    resultante = []
    r=0
    tamanho = len(lista)
    for coluna in range(tamanho):
        for linha in range(tamanho):
            r +=lista[coluna]*matriz[linha][coluna]
        resultante.append(r)
        r=0
    return resultante


def multMatrizMatriz(matrizA, matrizB):
    """Multiplica duas matrizes."""
    sizeLA = len(matrizA)
    sizeCA = len(matrizA[0]) 
    sizeCB = len(matrizB[0])
    matrizR = []
    # Multiplica
    for i in range(sizeLA):
        matrizR.append([])
        for j in range(sizeCB):
            val = 0
            for k in range(sizeCA):
                    val += matrizA[i][k]*matrizB[k][j]
            matrizR[i].append(val)
    return matrizR

def constroiLista(tamanho):
    lista = []
    for i in range(tamanho):
        lista.append(randint(1, tamanho+1))
    return lista

def constroiMatriz(tamanho):
    matriz = []
    matriz_temp = []
    for i in range(tamanho):
        for j in range(tamanho):
            matriz_temp.append(randint(1,tamanho+1))
        matriz.append(matriz_temp)
        matriz_temp = []
    return matriz

def main():
    tempos = []
    temposLM = []
    for i in range(1, 1000):        
        l1 = constroiLista(i)
        l2 = constroiLista(i)
        m1 = constroiMatriz(i)
        m2 = constroiMatriz(i)
        ini = time.time_ns()#nanosegundos
        somaListas(l1, l2)
        fim = time.time_ns()
        tempos.append(fim-ini)
        ######################
        ini1 = time.time_ns()
        multListaMatriz(l1, m1)
        fim1 = time.time_ns()
        temposLM.append(fim1-ini1)
        #######################
        ini2 = time.time_ns()
        multMatrizMatriz(m1, m2)
        fim2 = time.time_ns()
        temposLM.append(fim2-ini2)
        print(i)
    #plotar os tempos de execucao
    plt.plot(tempos)
    plt.plot(temposLM)
        
    

if __name__ == "__main__":
    main()