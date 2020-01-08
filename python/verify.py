import hashlib

def basic():
    h = hashlib.new('sha256')
    h.update(b"this is a test")
    h.hexdigest()

    fp = open("Block1.txt","r")

    fString = fp.readline().rstrip('\n')
    fString += fp.readline().rstrip('\n')
    fString += fp.readline().rstrip('\n')
    fString += fp.readline().rstrip('\n')

    print("file: <" + fString +">")

