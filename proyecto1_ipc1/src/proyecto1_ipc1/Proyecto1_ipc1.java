/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1_ipc1;

import java.util.Scanner;
import java.util.Random;
public class Proyecto1_ipc1 {

    /**
     * @param args the command line arguments
     */
    
    //Variables globales
    //Usaremos esta variable para el scanner:
    //En la linea 8 fue importada
    public static Scanner dato_entrada = new Scanner(System.in); 
    //Esta variable nos servira para crear numeros aleatorios
    //En la linea 9 es donde esta importada
    public static Random random = new Random();
    //Creamos la matriz sera global porque es mas sencillo trabajar sobre una sola
    //Verdadero en el caso del tablero significa que existe penalizacion
    //Falso en el caso del tablero significa que no hay nada
    public static boolean[][] tablero = new boolean[8][8];
    
    //Creamos una variable global donde almacenaremos la posicion del jugador
    //A lo largo del proyecto podran ver como esta se modifica para pintar al jugador en el tablero
    //Esta es de las variables mas importantes del proyecto
    public static int posicion_de_la_ficha = 1;
    
    public static void main(String[] args) {
        //Mandamos a llamar el metodo menu, para ver metodos y funciones en profundidad...
        //Consulten este video: https://www.youtube.com/watch?v=hDPSRbXBi8U
        menu();
    }
    //Esta funcion no hace nada, la hice en la clase
    public static void prueba(){
        int numeroBuscado = 35;

        for (int i = 1; i <= 100; i++) {
            if (i == numeroBuscado) {
                System.out.println("¡Número encontrado: " + i + "!");
                break;
            }
        }
    }
    
    //Creamos el metodo menu, recuerden los metodos son partes de codigo que nos ayudan a simplificarnos la vida
    //Estos no retoran nada a diferencia de las funciones
    //Este metodo fue llamada en la linea 35
    public static void menu(){
        //Instanciamos la variable a ingresar
        int opcion = 0;
        //Do: El do es lo que queremos que el la instrucción while repita continuamente
        //Un video que explica mejor el do while: https://www.youtube.com/watch?v=vHKWMR2WaIQ
        do{
            //Imprimimos, como podran darse cuenta el print de abajo no tiene el ln
            //Se hizo asi por razones esteticas, pero ustedes pueden optar a utilizar la forma que gusten
            //El \n significa salto de linea
            System.out.print("Bienvenido al menu \n 1.Iniciar Juego \n 2. Retomar Juego\n "
                    + "3. Datos de la Estudiante\n 4. Salir\n"
                    + "Seleccione una opción:");
            //Solicitamos el valor de la opcion
            opcion = dato_entrada.nextInt();
            
            //Instruccion switch, en escencia es casi lo mismo que un if
            //Video explicando switch case https://www.youtube.com/watch?v=T4X0C5bLM0A
            //Pueden tratar de usar el if con tal de afianzar sus conocimientos
            switch(opcion){
                //El caso es como si tuvieramos if(opcion == 1 ) si se dan cuenta
                //En la linea 69 estamos mandando como parametro a esa variable 
                case 1: 
                    //Se manda a llamar el metodo reiniciar_valores()
                    //Esto para limpiar la matriz por si ya se habia iniciado el juego previamente
                    reiniciar_valores();
                    System.out.println("Iniciando Juego...");
                    //El siguiente metodo es para iniciar el juego en cuestion
                    juego();
                    //La instruccion continue sirve para que regrese al inicio del bucle
                    //Un video que puede servirles: https://www.youtube.com/watch?v=C1W9nfdYlnI
                    //En el video se explica break y continue 
                    continue;
                case 2:
                    System.out.println("Retomando juego...");
                    //Esta segunda opcion no limpia la matriz, porque nos interesa conservar los valores que estan ahi
                    juego();
                    continue;
                case 3: 
                    //Funcion que sirve solo para imprimir los datos del estudiante
                    imprimir_datos_estudiante();
                    continue;
               //default es como el else, ya que si ninguna condicion previa se cumple 
               //Se ejecuta esta instruccion
                default:
                    continue;
            }
        //Recuerden que la funcion while siempre y cuando sea verdadera su condicion... 
        //Se va a repetir hasta que sea falso 
        }while(opcion != 4);
        //Un mensaje de despedida
        System.out.println("Has salido, buen dia :)");
    }
    
    
    //Es una buena practica comentar secciones de codigo, abajo puse INICIA SEGMENTO JUEGO...
    //Por si el proyecto se torna largo poder buscar esta seccion con ctrl+f
    //INICIA SEGMENTO JUEGO
    
    public static void reiniciar_valores(){
        //Ponemos la ficha en el 1 del tablero :)
        posicion_de_la_ficha = 1;
        //Limpiamos la matriz llenandola de falsos lo que significa que...
        //La matriz no tendria mas penalizaciones y quedara de una forma limpia
        
        //Recorremos la fila
        //Video explicando el for: https://www.youtube.com/watch?v=XW7LS1zwTME
        for (int i = 0; i < 8; i++) {
            //Recorremos la columna
            for (int j = 0; j < 8; j++) {
                //Ponemos el valor de la columna como falso :)
                tablero[i][j] = false;
            }
        }
        //Comenzamos a llenar la matriz con las zonas con penalizaciones cumpliendo...
        //Los requistos establecidos en la practica para las penalizaciones
        aleatorio_penalizacion();
    }
    
    //El siguiente metodo se encarga de mantener el flujo del juego, a continuacion...
    //Se detalla su funcionamiento
    public static void juego(){
        //Instanciamos la variable tecla
        //La tecla es el control del juego, si la tecla es p se va al menu principal
        String tecla = "";
        //Volvemos a tener el do, while, como les explique en clase
        //Esta instruccion nos sirve para repetirse hasta cumplir la condicion requerida
        do{
            //Se imprime el tablero, como se puede apreciar esta es la funcion que hace la magia
            //Mas adelante se explicara...
            imprimir_tablero();
            //Debajo del tablero se deja un mensaje para el usuario :)
            System.out.println("Pulsa cualquier tecla (p para salir)");
            //Volvemos a usar la variable del scanner que utilizamos anteriormente...
            //Yo lo hice asi para no tener que crear varias variables scanners pero es valido...
            //Trabajarlo de diferentes formas
            tecla = dato_entrada.next();
            //Si la tecla es distinta a p, significa que estamos jugando  y es por...
            //Ello que tenemos que tirar el dato unicamente en esta situacion :)
            if(tecla.charAt(0) != 'p'){
                //Tiramos al dado wuuu! Este por medio de un metodo llamado tirar_dado()
                tirar_dado();
                //Si la ficha del jugador esta por encima del 64, significa que ya gano
                if(posicion_de_la_ficha > 64){
                    //imprimir_tablero();
                    System.out.println("Felicidades! Has terminado!!");
                //En este caso usamos un break para romper con el ciclo, en clase...
                //Les mencione que el break se usaba mucho en "for", en este caso...
                //Tambien lo estoy usando en el while lo cual tambien es valido ya que...
                //Tambien puede romper el while
                    break;
                }
            }
        //Si la letra es p se rompe el while y al romperse termina la ejecucion del metodo
        //Al terminar el metodo este regresa a donde fue llamado ver linea aprox 83 
        //Nota, el metodo tambien se llama en la linea 91, en el caso de haber...
        //Sido ese el que se llamo ahi es donde termina la ejecucion
        }while(tecla.charAt(0) != 'p');
        
    }
    
    //Llegamos a donde se tira el dado, este mueve al jugador de posicion :)
    public static void tirar_dado(){
        //Se usa por primera vez el random aqui un video de como funciona:
        //https://www.youtube.com/watch?v=7HuKPl6vrn4
        //El + 2 nos sirve para que inicie en 2, es algo complicado de explicar...
        //Por eso mejor les dejo el video :)
        int numero_aleatorio = random.nextInt(5) + 2;
        //Le mostramos al usuario el resultado del dado :)
        System.out.println("DADO:"+ numero_aleatorio);
        //Sumamos lo que el dado nos dio el dado y se lo ponemos a la variable global...
        //Que creamos al inicio linea aproximada 31 :)
        posicion_de_la_ficha += numero_aleatorio;
        // Supongamos que la posicion actual es 10 y le sale 6
        //Entonces la nueva posicion seria 16 :)
        //En el siguiente metodo veran la importancia de este valor
    }
    
    //Llegamos a lo mas complicado de explicar jaja
    //Aqui es donde se refleja todo lo anteriormente explicado
    public static void imprimir_tablero(){
        //Iniciamos en 64 debido a que es mas sencillo ir de atras para adelante
        int contador = 64;
        //Miramos hacia donde nos dirigimos :)
        //Orden derecha iria como 49 50 51 52 (De manera ascendente por asi decirlo
        //Orden izquierda 64 63 62 61 (De manera descendete :)
        boolean recorrido_derecha = true;
        
        //Crearemos otro boolean para determinar si el jugador ha caido en una trampa :)
        boolean caimos_en_trampa = false;
        
        //Comenzamos a recorrer la fila
        for (int i = 0; i < 8; i++) {
            //Ya estamos en la fila,ahora veremos si tenemos que...
            //Imprimir hacia la izquierda o derecha
            if(!recorrido_derecha){
            //Ya sabiendo como debemos de imprimir entonces comenzamos a imprimir las columnas :)
            for (int j = 0; j < 8; j++) {
                //Comenzamos escribiendo el corchete
                System.out.print("[");
                //Si el contador y la posicion de la ficha coinciden...
                //Entonces debemos de imprimir nuestra ficha en esta casiila...
                //Si ejecutan el codigo se podran dar cuenta de esto
                if(posicion_de_la_ficha == contador){
                    //Como pueden ver los print no llevan ln, esto es para que no...
                    //Se arruine la presentacion,pueden jugar ustedes con todo esto
                    //NOTA INTENTEN IMPRIMIR DE UNA FORMA DIFERENTE EL TABLERO ASI REDUCEN EL RIESGO DE FUNA :)
                    System.out.print("@");
                }
                //A continuacion la siguiente condicion puede ser polemica porque...
                //Como es posible que una matriz este dentro de una condicion y esta haga algo??
                //Pues recuerden que nuestra matriz es de tipo booleano
                //Entonces en la posicion i j puede haber tanto verdadero como falso...
                //Y es a esto que se hace realmente referencia
                if(tablero[i][j]){
                    //Al ser verdadero significa que estamos en casilla trampa
                    //Por lo tanto hay que demostrarlo imprimiedo el #
                    System.out.print("#");
                    //Aqui queremos ver si el jugador cayo en una trampa
                    if(posicion_de_la_ficha == contador){
                        //Modificamos la variable
                        caimos_en_trampa = true;
                    }
                }
                //Terminamos la impresion
                System.out.print(contador +"] ");
                //Como se explico arriba es necesario restar  para ir en ese orden:
                //Orden izquierda 64 63 62 61 (De manera descendete :)
                //Aqui un video que explica esto: https://www.youtube.com/watch?v=J_HZWQzt5So
                contador--;
            }//Termino el for de la columna
            //Cambiamos la direccion...
            //Esto con el fin de lograr el camino serpenteado :)
            recorrido_derecha = true;
            }else{
                //Restamos este valor supongamos que:
                //[#56][55][#54][53][#52][51][#50][49] 
                //Si no restamos le 7 veremos que nuestro contador esta en 48 
                //Descomentando la siguiente impresion podran ver eso
                //System.out.println(contador);
                contador -= 7;
                //48 - 7 = 41
                //Es asi como comenzaremos a imprimir:
                //[41] [42] [43] [44] [#45] [#46] [47] [48] 
                //Como pueden ver vamos ascendentemente...
                //En los siguientes pasos veran bien como queda todo
                //Seguimos con el for con la fila
                for (int j = 0; j < 8; j++) {
                    //Lo siguiente ya se explico arriba :)
                    System.out.print("[");
                    if(posicion_de_la_ficha == contador){
                        System.out.print("@");
                    }
                    if(tablero[i][j]){
                        System.out.print("#");
                        if(posicion_de_la_ficha == contador){
                            caimos_en_trampa = true;
                        }
                    }
                    System.out.print(contador +"] ");
                    contador++;
                } 
                //Al igual que lo hicimos arriba volvemos a cambiar la direccion...
                //Como pueden ver se van alternando! :) izq der izq der
                recorrido_derecha = false;
                //Esta resta la hago para retornar el contador al valor que necesito para la...
                //Impresion izquierda retomando...
                //[41] [42] [43] [44] [#45] [#46] [47] [48]
                //Terminamos en 48 pero por contador++ linea aprox 275 terminamos en 49 realmente...
                //Es por eso que restamos 9 49 - 9 = 40 :) y la siguiente fila al haber cambiado orden
                //Quedaria asi:
                //[#40][39][38][37][#36][#35][34][33] 
                contador-=9;
            }
            //Al terminar hacemos un salto de linea para darle buena forma al tablero :)
            System.out.println("");
        }
        
        //Habiendo terminado de imprimir la matriz es buen momento para ver si estamos en el castigo
        //Para ello utilizaremos la variable booleana en la linea aprox 236 caimos_en_trampa
        if(caimos_en_trampa){
            System.out.println("OH NO, CAIMOS EN TRAMPA!");
            //No termine esto, les dejo el resto chicas! Ustedes pueden!!
        }
        
    }
    
    //Esto nos sirve para ver las penalizaciones  
    public static void aleatorio_penalizacion(){
        //Instanciamos la variable
        int numero_aleatorio = 0;
        //Como son 8 filas las que debemos de trabajar...
        //Veremos la cantidad de castigos que habran por fila 2 a 4
        for(int i = 0; i < 8; i++){
        numero_aleatorio = random.nextInt(3) + 2;
        //Al descomentar aqui podran ver cuantos castigos por fila habran :)    
        //System.out.println(numero_aleatorio);
        //Ahora pondremos los castigos en las filas :)
            llenar_tablero_penalizaciones(i, numero_aleatorio);
        }
        
    }
    
    public static void llenar_tablero_penalizaciones(int pos_x, int numero_penalizaciones){
        //Vamos a crear un nuevo aleatorio, para poner en cualquiera de las filas...
        //Una penalizacion :)
        int numero_aleatorio = 0;
        //Recuerden que ya estamos dentro de un for de la fila, para las columnas...
        //Es necesario otro for con un limite de las penalizaciones para...
        //Poner el numero de penalizaciones que nos salieron entre 2 y 4
        //Por ejemplo en caso de tener 3 penalizaciones el for se repetira 3 veces
        for(int i = 0; i < numero_penalizaciones; i++){
            //Un booleano para ver si se repite
            //Con repetir me refiero a evitar poner el valor true en la misma casiila
            boolean no_se_repite = false;
            //Necesitamos que no se repitan, para eso es este do while
            //Porque en caso de repetirse lo va a intentar hasta lograr llenarse con...
            //El numero correcto de penalizaciones :)
            do{
                numero_aleatorio = random.nextInt(8);
                //En caso de repetirse el continue vuelve a iniciar con el while 
                if(tablero[pos_x][numero_aleatorio]){
                    continue;
                }
                else{
                    //Ponemos la penalizacion
                    tablero[pos_x][numero_aleatorio] = true;
                    //Evitamos que la penaizacion quede en la casiila donde esta el uno
                    //En caso de que la haya puesto ahi se repite todo por el continue 
                    if(tablero[7][7]){
                        tablero[7][7] = false;
                        continue;
                    }
                    no_se_repite = true;
                }
            }while(!no_se_repite);
        }
    }
    //FIN DEL JUEGO
    
    //Datos del estudiante! Wow
    public static void imprimir_datos_estudiante(){
        System.out.println("Nombre: "
                + "Carnet: ");
    }
    
    //Pendientes: Los problemas matematicos
    //Poner las condiciones para la dificultad
    //Buena suerte chicas! No se desanimen!!! No dejar de intentarlo hasta que el semestre acabe...
    //Es una prioridad! Exitos!!
    
}
