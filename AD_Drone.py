import socket
import sys

HEADER = 64
PORT = 5050
FORMAT = 'utf-8'
FIN = "FIN"

def send(msg):
    message = msg.encode(FORMAT)
    msg_length = len(message)
    send_length = str(msg_length).encode(FORMAT)
    send_length += b' ' * (HEADER - len(send_length))
    client.send(send_length)
    client.send(message)
    
########## MAIN ##########


print("****** INICIALIZANDO AD_DRONE ****")

if  (len(sys.argv) == 3):
    SERVER_REGISTRY = sys.argv[1]
    PORT_REGISTRY = int(sys.argv[2])
    ADDR_REGISTRY = (SERVER_REGISTRY, PORT_REGISTRY)

    # SERVER_ENGINE = sys.argv[3]
    # PORT_ENGINE = int(sys.argv[4])
    # ADDR_ENGINE = (SERVER_ENGINE, PORT_ENGINE)

    # SERVER_BOOTSTRAP = sys.argv[5]
    # PORT_BOOTSTRAP = int(sys.argv[6])
    # ADDR_BOOTSTRAP = (SERVER_BOOTSTRAP, PORT_BOOTSTRAP)
    
    
    client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client.connect(ADDR_REGISTRY)
    print (f"Establecida conexión en [{ADDR_REGISTRY}]")

    msg=sys.argv[3]
    while msg != FIN :
        print("Envio al servidor: ", msg)
        send(msg)
        print("Recibo del Servidor: ", client.recv(2048).decode(FORMAT))
        msg=input()

    print ("SE ACABO LO QUE SE DABA")
    print("Envio al servidor: ", FIN)
    send(FIN)
    client.close()
else:
    print ("Oops!. Parece que algo falló. Necesito estos argumentos: <ServerIP AD_Engine> <Puerto AD_Engine>")
