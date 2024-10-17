#include <iostream>
using namespace std;
float function(int s,int n,float p){
    if (n==1)
        return (s+1)/p;
    else 
        {
        return function(s+p,n-1,p*(n-1));
        }
}
int main(){
    int n;
    cout<<"please enter the number n:";
    cin>>n;
    cout<<function(0,n,n);
}