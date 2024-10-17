# the void function approach
def print_r(arr, c):
    if len(arr) == c:
        pass
    else:
        print_r(arr, c+1)
        print(arr[c], end=",")


array = input("please enter the numbers :").split()
array = [int(item) for item in array]
print_r(array, 0)
