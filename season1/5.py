def max(arr, maximum, c):
    if c == len(arr)-1:
        return maximum
    else:
        if arr[c] > maximum:
            maximum = arr[c]
        return max(arr, maximum, c+1)


array = input("please enter the numbers :").split()
array = [int(item) for item in array]
print(max(array, array[0], 0))

