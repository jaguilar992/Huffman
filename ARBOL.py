class ARBOL(object):
	def __init__(self):
		#Constructor
		self.raiz=-1
		self.hijo_mas_izq=[]
		self.data=[]
		self.hermano_dere=[]

	# PADRE
	def PADRE(self,nodo):
		index=self.indice_nodo(nodo)
		if (nodo!=self.data[self.raiz] and index!=-1):
			#PRIMERO: Comprobar si el nodo es hijo izquierdo de otro
			hijo_de=self.ES_HIJO_IZQ_DE(nodo)
			if (hijo_de!=-1):
				return self.data[hijo_de]
			else:
				# Sino, se creara lista de hermanos izquierdos del nodo
				hermanos=[]
				hermanos.append(index)
				h_der = self.INDICE_HERMANO_IZQ(self.data[index])
				while (h_der!=-1):
					hermanos.insert(0,h_der)	
					h_der=self.INDICE_HERMANO_IZQ(self.data[h_der])
			index_her_mas_izq=int(hermanos[0])
			return self.data[self.ES_HIJO_IZQ_DE(self.data[index_her_mas_izq])]
		else:
			return None

	# HIJO_MAS_IZQ
	def HIJO_MAS_IZQ(self,nodo):
		for i in range(0, len(self.data)):
			if self.data[i]==nodo:
				j=self.hijo_mas_izq[i]
				if j!=-1:
					return self.data[j]
				else:
					return None
		return None

	# HIJO_MAS_DER
	def HIJO_MAS_DER(self,nodo):
		for i in range(0,len(self.data)):
			if self.data[i]==nodo:
				j=self.HIJO_MAS_IZQ(nodo)
				if j!=None:
					hijo=None
					h_der=self.HERMANO_DER(j)
					while h_der!=None:
						hijo=h_der
						h_der=self.HERMANO_DER(h_der)
					return hijo
				else:
					return None
		return None

	# HERMANO_DER
	def HERMANO_DER(self,nodo):
		for i in range(0, len(self.data)):
			if self.data[i]==nodo:
				j=self.hermano_dere[i]
				if j!=-1:
					return self.data[j]
				else:
					return None
		return None

	# HERMANO_IZQ
	def HERMANO_IZQ(self,nodo):
		index=self.indice_nodo(nodo)
		hermano_izq=-1
		for i in range(1,len(self.data)):
			if self.hermano_dere[i]==index:
				hermano_izq=i
				break
		return self.data[hermano_izq]
	
	# ETIQUETA
	def ETIQUETA(self, nodo):
		if nodo in self.data:
			return nodo
		else:
			return None

	#RAIZ
	def RAIZ(self):
		return self.raiz

	# ANULA
	def ANULA(self):
		self.raiz=-1
		self.hijo_mas_izq=[]
		self.data=[]
		self.hermano_dere=[]

	def CREA0(self,nodo):
		m=self.memoria()
		self.data[m]=nodo
		return m

	def CREAi(self,nodo,Ak):
		m=self.memoria()
		self.raiz=m
		self.data[m]=nodo
		self.hijo_mas_izq[m]=Ak[0]
		self.hermano_dere[m]=-1
		for i in range(0,len(Ak)-1):
			#self.hijo_mas_izq[Ak[i]]=-1
			self.hermano_dere[Ak[i]]=Ak[i+1]
		#self.hermano_dere[Ak[len(Ak)-1]]=-1
		#self.hijo_mas_izq[Ak[len(Ak)-1]]=-1
		return m

	################# METODOS DE ACCESO SIMPLIFICADO
	def ES_HIJO_IZQ_DE(self,nodo):
	#Retorna el indice del padre del nodo, a traves de una busqueda
	#Padre-Hijo mas izquierdo
	#Sino, devuelve -1
		index = self.indice_nodo(nodo)
		hijo_izq_de = -1
		for i in range(0,len(self.data)):
			if self.hijo_mas_izq[i]==index:
				hijo_izq_de=i
				break
		return hijo_izq_de


	def indice_nodo(self,nodo): 
	# Retorna el indice de un nodo en self.data
		if nodo in self.data:
			return self.data.index(nodo) # Pretty easy LOL
		else:
			return -1

	def INDICE_HERMANO_IZQ(self,nodo):
	# Retorna el indice del hermano izquierdo de un nodo
	# Por defecto si no posee, devuelve -1
		index=self.indice_nodo(nodo)
		hermano_izq=-1
		for i in range(1,len(self.data)):
			if self.hermano_dere[i]==index:
				hermano_izq=i
				break
		return hermano_izq

	def memoria(self):
		self.data.append(0)
		self.hermano_dere.append(-1)
		self.hijo_mas_izq.append(-1)
		return len(self.data)-1