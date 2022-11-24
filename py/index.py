import base64, io, sys
from PIL import Image
import torch

sys.stderr = io.TextIOWrapper(sys.stderr.detach(), encoding = 'utf-8')

base64String = sys.stdin.read()

imgdata = base64.b64decode(base64String)
dataBytesIO = io.BytesIO(imgdata)
image = Image.open(dataBytesIO)

# Model
model = torch.hub.load('ultralytics/yolov5', 'yolov5s', _verbose=False)
# Inference
results = model([image], size=640) # batch of images
# print(results)

print("감자전")