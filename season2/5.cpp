#include <iostream>
using namespace std;
void changeValue(int arr[][],int n){
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (i ==j)
                arr[i][j] +=1;
            if (i+j == n-1)
                arr[i][j] -= 1;
            if (i==j-1 || i+j ==n-2)
                arr[i][j] += 2;
            if (i-1==j || i+j ==n)
                arr[i][j] -= 2;
            if (i-1==j || i+j ==n-2)
                arr[i][j] += 3;
            if (i==j-1 || i+j ==n)
                arr[i][j] -= 3;
        }
        
    }
}   

int main(){
    int n;
    cout << "Enter the size of the matrix (n): ";
    cin >> n;
    int arr[n][n];
    cout << "Enter the elements of the " << n << "x" << n << " matrix:" << endl;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> arr[i][j];
        }
    
    }
    changeValue(arr,n);
}