# the function which return the reversed array
def print_r(arr, temp, j, k):
    if k == -1:
        return temp
    else:
        temp[k] = arr[j]
        return print_r(arr, temp, j+1, k-1)


array = input("please enter the numbers :").split()
array = [int(item) for item in array]
temp=[0 for item in array]
print(print_r(array, temp, 0, len(array)-1))
