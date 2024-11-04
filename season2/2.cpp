#include <iostream>
using namespace std;
int c(int n,int r){
    if( n==1 || n==2)
        return 1;
    if ((n-r)>r)
        r=n-r;
    int arr[2*(n-r+1)];
    arr[0]=1;
    for (int i = 1; i <= n-r; i++)
    {
       arr[i]=arr[i-1]*(r+i);
    }
    for (int i = 1; i <= n-r; i++)
    {
       arr[n-r+i]=arr[n-r+i-1]/i;
    } 
    //     for (int i = 0; i <=2*(n-r); i++)
    // {
    //   cout<<arr[i]<<endl;
    // } 
    return arr[2*(n-r)];
}   
int main(){
    cout <<"enter two numbers(n and r):";
    int n;
    int r;
    cin >> n>>r;
    cout<<c(n,r);
}