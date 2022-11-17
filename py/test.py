import sys, io

sys.stderr = io.TextIOWrapper(sys.stderr.detach(), encoding = 'utf-8')

argv = sys.argv
print(argv)
print("감자")
print("햄버거")
print("치킨")