#include <iostream>
using namespace std;
int f(int n){
    if( n==1 || n==2)
        return 1;
    int arr[n] = {0};
    arr[0]=1;
    arr[1]=1;
    for (int i = 2; i < n; i++)
    {
       arr[i]=arr[i-1]+arr[i-2]; 
    }
    return arr[n-1];


}
int main(){
cout <<"enter a number:";
int n;
cin >> n;
cout<<f(n);

}
