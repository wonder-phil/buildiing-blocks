import hashlib

h = hashlib.new('sha256')
h.update(b"this is a test")
h.hexdigest()
