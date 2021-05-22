import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

df = pd.read_csv('/Users/munat/Desktop/fuzzy-c-means/multidim_withoutGUI/output',',')
#print(df.dimension)
#print(df[df.dimension==2][df.cluster_size==2])
#print(df)
#df.to_excel("table.xlsx",sheet_name="iteration count due to m values", index=False)
for i in range(2,10):
    for j in range(2,10):
        fig, ax = plt.subplots(1)
        ax.set_xlabel("m values")
        ax.set_ylabel("iteration count")
        ax.plot(df[df.dimension==i][df.cluster_size==j].m_value, df[df.dimension==i][df.cluster_size==j].iteration_count)
        fig.figsize=(20,20)
        a = "dimension:"+str(i)+";"+"cluster_size"+str(j)+".pdf"
        plt.savefig(a)
