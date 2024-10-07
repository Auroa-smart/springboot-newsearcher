# springboot-newsearcher
基于爬虫技术的新闻分类检索系统

1.	整体设计
1.1	项目概述
背景：随着互联网的迅速发展，新闻和信息的数量不断增加，用户需要一种有效的方式来浏览和查找感兴趣的新闻文章。然而，传统的新闻网站和搜索引擎在新闻分类和检索方面存在一些限制。本项目的背景是开发一个新闻分类检索系统，利用爬虫技术从互联网上获取新闻文章，对其进行自动分类，然后提供用户友好的界面来实现高效的新闻检索。
项目目标：本项目的主要目标是开发一个功能强大的新闻分类检索系统，以提供以下功能：
1.新闻爬取：利用网络爬虫技术从多个新闻网站获取新闻文章的文本数据，并且每隔一段时间就进行网站更新。
2.自动分类：利用自然语言处理（NLP）和机器学习技术，自动将新闻文章分类到相关的主题或类别中。
3.用户界面：提供用户友好的网页界面，使用户能够轻松查找和筛选新闻文章。
4.高效检索：开发高效的新闻检索引擎，支持关键字搜索、时间筛选和分类检索。

1.2	需求分析
1.2.1	用例图设计
 ![image](https://github.com/user-attachments/assets/643e5099-610a-4615-8be0-e45db13b5369)

基于爬虫技术的新闻分类系统用例图
1.2.2	用例说明
系统用户用例分析
表1-1 系统用户角色描述
角色名称	   角色描述	     角色功能
  用户	   系统服务对象	 登录注册，新闻浏览，新闻分类检索 
系统管理员	 系统管理者	   用户管理

用户用例分析
 ![image](https://github.com/user-attachments/assets/2b488d0c-b157-4653-a0e5-6c8a7d3b9ee7)
系统用户用例图

用户拥有注册登录，新闻浏览，新闻检索的功能。
（1）登录模块是适用于所有用户的，用例如表3-2所示。
表1-2 登录模块用例说明表
![image](https://github.com/user-attachments/assets/3bc4ddca-6057-48fa-802b-8154edc3bae5)

2、用户点击登录
后置条件	点击按钮后进入具体功能界面

（2）注册模块也是适用于所有用户的，用例如表3-2所示。
表1-3 注册模块用例说明表
![image](https://github.com/user-attachments/assets/6a988890-e105-437b-9e32-b53e277df60e)

（3）新闻浏览模块根据指定条件查询相关新闻。
表1-4 新闻里浏览模块用例说明表
![image](https://github.com/user-attachments/assets/093e2a74-4952-4ac4-8b79-1505a6a6637e)

（4）新闻检索模块可以检索到指定分类的所有新闻列表。
表1-5新闻分类检索用例说明表
![image](https://github.com/user-attachments/assets/dc281e09-d8f9-4919-8d6d-dc221e44cf19)

系统管理员用例分析
 ![image](https://github.com/user-attachments/assets/64754dc2-1016-4a97-b19c-81ac82c315b7)

系统管理员用例图
根据用例图，系统管理员负责的功能模块，以用例表进行说明：
（1）登录模块是适用于所有用户的，用例如表3-2所示。
表1-6 登录模块用例说明表
![image](https://github.com/user-attachments/assets/342edf5f-985a-424d-a0d5-6833178ff1a9)


（2）用户管理模块，用例如表3-3所示。
表1-7 用户管理模块用例说明表


1.3	技术选型
1.3.1	前端(Vue.js+Element)
Vue+Element框架
Vue是一款用于构建用户界面的JavaScript框架。它基于标准HTML、CSS和JavaScript构建，并提供了一套声明式的、组件化的编程模型，帮助开发者高效地开发用户界面。Vue.js是一套构建用户界面的渐进式框架，采用自底向上增量开发的设计，核心库只关注视图层。另一方面，Vue完全有能力驱动采用单文件组件和Vue生态系统支持的库开发的复杂单页应用。其最大的特点是易用、灵活、性能好。
![image](https://github.com/user-attachments/assets/176e92aa-171a-476e-bcca-7a4d8ce1f0f2)
Vue项目创建流程图

1.3.2	后端(Springboot + Mybatis-plus)
本系统开发采用java语言，java比较常见的主流框架有SpringMVC+Spring+MyBatis，SpringMVC+Struts2+Hibernate，Springboot+MyBatis等。
本系统采用Springboot+ MyBatis-Plus的框架，SpringBoot是由Pivota团队提供的全新框架，其设计目的是用来简化Spring应用的 初始搭建以及开发过程。
SpringBoot程序优点：①自动配置②起步依赖（简化依赖配置）③辅助功能（内置服务器，…）。下面将介绍各个框架以及它在系统中的应用。

Springboot
![image](https://github.com/user-attachments/assets/cf207381-9854-49e1-95f5-21fb41614ec7)
springboot项目创建流程图
Springboot的三层架构
1.	表现层（Presentation Layer）：
•	视图（View）：负责用户界面的展示和用户交互。
•	控制器（Controller）：接收用户的请求并协调调度其他层的处理。
2.	业务逻辑层（Business Logic Layer）：
•	服务层（Service）：包含业务逻辑的实现，协调各个领域对象的交互。
•	模型层（Domain/model）：包含业务领域对象（实体、值对象）以及相关的业务规则和行为。
3.	数据访问层（Data Access Layer）：
•	存储库（Repository）或数据访问对象（DAO）：负责与数据存储（如数据库）进行交互，提供数据的持久化和检索。
在这个架构中，数据流从表现层开始，用户通过视图发起请求，请求被控制器接收并传递给服务层。服务层包含业务逻辑的实现，可能会涉及多个领域对象的协作。服务层通过调用存储库或数据访问对象来获取或持久化数据。
领域层是业务逻辑的核心，包含了业务领域对象（实体、值对象）以及相关的业务规则和行为。领域对象通过对数据进行状态变更、计算和验证等操作来实现业务功能。
数据访问层负责与数据存储进行交互，包括对数据库的访问和操作。它提供了数据的持久化和检索功能，并为领域层提供数据访问的接口。
Springboot整合前端Vue可以直接把整个Vue项目文件夹复制到Springboit项目的根目录文件夹下。
Mybatis-plus
MyBatis-Plus (opens new window)（简称 MP）是一个 MyBatis (opens new window)的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。以下是Mybatis-plus的搭建过程。
![image](https://github.com/user-attachments/assets/6eecffed-07a1-4f97-b578-a93f0fe891c0)
Mybatis-plus搭建过程
Mybatis-plus的核心组件包括：
Autogenerator：代码生成器，用于生成实体类、Mapper接口、Service和Controller等代码。这样可以加速开发过程并保持开发的一致性。（但是本文为了更好的学习框架并没有使用此组件）
BaseMapper：通用Mapper接口，包含了一系列通用的“Wrapper”：查询条件构造器，用于创建灵活的查询条件。只需继承此接口并使用注解来生成SQL，无需手动编写SQL语句。
QueryWrapper：查询条件构造器的一种具体实现，提供了“UpdateWrapper”：更新条件构造器，用于更新操作的条件床=创建。“LambdaQueryWrapper”：使用Lambda表达式进行条件构造的方式，类型更加安全。“LambdaUpdateWrapper”：使用Lambda表达式进行更新条件构造的方式。
Page：分页查询的封装，用于处理分页查询的结果。用户可以方便地进行分页查询操作。

1.4 系统架构设计
新闻分类系统的逻辑架构主要分为5层，分别是表示层，控制层，业务逻辑层，数据访问层，持久层。表示层用于前端展示，主要使用Vue框架和Element UI框架完成。实现接口由控制层完成，数据以json格式传输。业务逻辑层包括用户端和管理端，用户端实现新闻查询，新闻分类检索等功能；管理端实现对用户信息的增删改查功能。数据访问层主要用于类的编写。持久层以Mybatis-plus对数据库进行读写操作。
![image](https://github.com/user-attachments/assets/d78dc1e0-de1f-4674-8534-efdbb2305649)
 
系统架构图
2.	详细设计
2.1 数据库设计
分析新闻与类别之间的关系，画出对应的类图，如图4-4所示。

![image](https://github.com/user-attachments/assets/b5e54e6c-00a4-4b1b-9550-1bf288a2aae9)
数据库对应类图

为实现新闻网站内容的存储和检索功能，首先需要一个新闻文章表，用于存储每篇新闻文章的信息，然后需要一个主题分类表，和新闻分类关联表，存储每个分类的相关信息以及将新闻文章与其所属的主题分类关联起来。具体表定义如下。
表2-1 用户信息表user
![image](https://github.com/user-attachments/assets/277c0e80-4a0b-4846-a28a-59a7fb008936)

表2-2新闻文章表news
![image](https://github.com/user-attachments/assets/4bea0789-352a-4492-b440-4580944bf818)

表2-3类别表category
![image](https://github.com/user-attachments/assets/6da6e0f6-a386-4fff-b4ff-1a10a6441893)

表2-4新闻分类关联表news_category
![image](https://github.com/user-attachments/assets/ad74f4ff-8429-4344-8272-260a677a9867)	

2.2 爬虫模块设计
Java开源的爬虫框架有Jsoup、WebMagic、Crawler4j、ApacheNutch、Spring Batch、Selenium、HttpClient、Jaunt、HtmlUnit、WebSphinix等等，为了更好地学习爬虫技术，分析了几种主流的爬虫框架，决定使用最原始的两大框架Jsoup+HttpUnit实现项目爬虫模块。
jsoup可以获取静态页面，并解析页面标签，最主要的是，可以采用类似于jquery的语法获取想要的标签元素。HttpUnit主要作用是帮助开发者执行自动化的 Web 应用测试和模拟用户操作，本文利用其进行请求模拟过程。具体来说，HttpUnit 提供了以下功能和作用：1. 模拟用户操作：HttpUnit 可以模拟用户在浏览器中执行的各种操作，如点击链接、填写表单等。2. 处理 HTTP 请求和响应：HttpUnit 允许开发者发送 HTTP 请求，并接收和处理 Web 服务器的响应。
爬虫基本步骤为以下四步： 
（1）获取首页；
（2）使用jsoup获取新闻<a>标签；
（3）从<a>标签中抽取基本信息，封装成News对象；
（4）根据新闻url访问新闻页面，获取新闻内容、图片等。
![image](https://github.com/user-attachments/assets/66513cc4-a7f2-4c55-9cf9-6a02dfe101f3)
 
网页爬取：首先需要使用Jsoup和HtmlUnit的Java库来获取HTML内容。定义一个专门的类来处理页面抓取，并提供接口来定义抓取规则，以实现不同网站新闻内容的抓取。
页面解析：获取了页面内容后，进一步解析HTML以提取新闻信息，需要对不同的网站的HTML标签、CSS选择器进行解析。
数据存储：将解析得到的新闻数据存储到MYSQL数据结构中。
URL管理：为避免重复抓取同一页面，设计了一个URL列表作为URL管理器来跟踪已经访问过的URL，确保不会重复抓取相同的新闻页面。
深度控制：为了避免陷入死循环，需要控制爬虫的深度，即抓取到多少层的链接，本文将深度参数设置为5。
定时任务：为了定期更新新闻数据，需要使用一个定时任务来定期运行爬虫程序。
![image](https://github.com/user-attachments/assets/2f77dd9c-6c61-42f7-8a3e-b1dbfb03e45e)
爬虫模块类关系图

设置定时任务定期更新新闻网站
Springboot进行定时任务一共有三种方式：第一种也是最简单的一种：基于注解 (@Scheduled)的方式；第二种：基于接口 (SchedulingConfigurer)；第三种：基于注解设定多线程定时任务。
本项目采用第一种方式。在启动类中用注解 @EnableScheduling进行标注，表明此类存在定时任务。然后在定时执行的方法之上添加注解@Scheduled，使系统每隔24小时就运行一次爬虫程序，更新数据库数据，重新刷新新闻内容界面等等，实现新闻网站的自动更新。
2.3 分类模块设计
HanLP
HanLP是由一系列模型与算法组成的Java工具包，目标是普及自然语言处理在生产环境中的应用。HanLP是一款自然语言处理工具包,提供了一系列强大的算法和模型来处理中文文本。HanLP具备功能完善、性能高效、架构清晰、语料时新、可自定义的特点。HanLP提供中文分词、词性标注、命名实体识别、关键词提取、短语提取、拼音转换等多种功能。
本项目使用HanLP先对新闻文本进行预处理，包括中文分词、去除停用词、特征向量化表示等，可以更好地结合k-means算法实现文本聚类任务，提高对大规模文本数据的处理效率和分析能力。
k-means算法
机器学习分为“有监督学习”和“无监督学习”，主要区别在于有监督学习通常是利用带有专家标注的标签的训练数据，学习一个从输入变量X到输入变量Y的函数映射；而无监督学习问题处理的是，只有输入变量X没有相应输出变量的训练数据。它利用没有专家标注的训练数据，对数据的结构进行建模。
在“无监督学习”中，应用最广的是“聚类”，聚类就是将数据集中的样本划分为若干个通常是不相交的子集，每个子集称为一个“簇”。经过划分后，每个簇对应的概念语义需要自行命名。
基于本项目，需要将爬取到的新闻数据进行无专家标签的分类，明显属于无监督的机器学习模型，因此本文决定采用经典的原型聚类算法即K-means算法对新闻数据进行聚类分析。
K-means算法对聚类所得簇划分最小化平方误差，即计算簇内样本围绕簇均值向量的紧密程度，所得值越小则簇内相似度越高。聚类的依据是文本的相似性。具体来说，K均值聚类算法将新闻文本转化为特征向量，并根据这些特征向量将新闻分为不同的簇。每个簇包含在特征空间中相似的新闻。一个簇就算是一种类别。
为了更好地使用K-means算法，需要先对原始新闻数据进行分词、去停用词和特征值提取处理，然后运用聚类算法将新闻转为特征向量列表进行聚类，最终得到k个簇。
![image](https://github.com/user-attachments/assets/993a39fe-0c07-454e-8ea2-8f5f49d9c11a)
分类模块流程图
K-means算法的流程如下：
![image](https://github.com/user-attachments/assets/16df95d9-b210-4f52-8295-504bf357bc8c)
k-means算法流程图
3.	项目实现
3.1	登录模块的实现
用户点击登录按钮进入登录界面，输入用户名和密码，再次点击登录按钮，检查用户名和密码都正确后即可登录成功进入首页。
业务层主要负责新增、登录、注册、查询用户业务。

跨域问题
![image](https://github.com/user-attachments/assets/e9be0f69-db08-4d77-9f8d-33c8494f5f65)
出于浏览器的同源策略限制。同源策略（Sameoriginpolicy）是一种约定，它是浏览器最核心也最基本的安全功能，如果缺少了同源策略，则浏览器的正常功能可能都会受到影响。可以说Web是构建在同源策略基础之上的，浏览器只是针对同源策略的一种实现。同源策略会阻止一个域的。javascript脚本和另外一个域的内容进行交互。所谓同源（即指在同一个域）就是两个页面具有相同的协议(protocol)，主机(host)和端口号(port)。
所谓跨域就是从 A 向 B 发请求，如若他们的地址协议、域名、端口都不相同，直接访问就会造成跨域问题（vue：8080；springboot:8023），跨域是非常常见的现象！请求是跨域的但并不一定会报错，普通的图片请求,css文件请求是不会报错的。报错的条件是浏览器的同源策略，且发送Ajax请求，跨域是客户端问题。

3.2 首页的实现
首页展示新闻列表，用户可以根据输入的新闻标题、新闻来源、新闻发布日期实现新闻查询。这部分主要使用Mybtais-plus的核心组件Page和QueryWrapper实现分页查询功能。

3.2	用户管理模块的实现
用户管理模块包括查询所有用户、批量删除、根据id删除、分页查询功能。

3.4 新闻爬取模块的实现
新闻爬取
定义一个名为NewsPuller的接口，接口有一个pullNews和getHtmlFromUrl的方法，其中getHtmlFromUrl方法可以获取制定url的网页内容。NetEasyNewsPuller、IfengNewsPuller、SohuNewsPuller分别为爬取网易新闻、凤凰新闻、搜狐新闻的工具类，都实现该接口方法对获取到的不同新闻网页进行分析，提取出对应的新闻内容。
 ![image](https://github.com/user-attachments/assets/3013eba4-87dc-4c32-bda1-3e6893e39a1d)
爬取搜狐新闻数据
定期更新
Springboot提供了一种方便的方式来创建和管理定时任务，通过使用“@Scheduled”注解，可以创建定时任务。
首先需要创建一个定时任务的类，用“@Scheduled”注解设置任务的触发时间。
然后在应用的主类上，使用“EnableScheduling”注解来启用Springboot的定时任务功能，在Springboot项目运行后，定时任务就会在指定的时间间隔内执行。


3.5 新闻分类模块的实现
实现k-means算法首先要进行新闻文本预处理，然后构建词汇表，统计所有新闻文本出现的不同的词语，再将新闻列表转化为特征向量，将新闻内容表示为一个特征向量，其中每个维度对应词汇表中的一个词语，而每个值表示该词语在新闻中出现的次数，然后执行k-means算法进行聚类。采用特征向量和簇的数量K作为参数，并返回一个包含K个簇的列表。
![image](https://github.com/user-attachments/assets/56d5f2c4-5486-449c-ada1-bc794639f8cb)
k-means算法得到簇分类
分类结果不均匀原因分析：
1.	数据不平衡：爬取到的新闻某些类别的数量远远超过其他类别，k均值聚类就可能会倾向于将更多的样本分配给那些较大的类别。
2.	k值选择：k值设置的不合适也可能会导致不均匀的分类结果。
3.	特征选择：特征向量的构建方式可能导致一些特征在聚类过程中占主导地位，从而影响了分类结果。
4.	项目成果
![image](https://github.com/user-attachments/assets/7cb49942-5b3b-48a8-bcd1-4cf81d0cf047)
登录界面
 ![image](https://github.com/user-attachments/assets/1719c142-d3da-42a1-bf0b-8323ae021f0a)
注册界面
 
![image](https://github.com/user-attachments/assets/90e12864-b30d-449c-8de3-aeeb1d6a98cc)
用户管理界面
 ![image](https://github.com/user-attachments/assets/94c29607-6c7f-4ce5-9acc-42c52ceb2bbf)
首页新闻列表图示
![image](https://github.com/user-attachments/assets/d5845f00-8be5-45de-b229-7da53f757468)
新闻分类界面
5.	实践心得和总结
待改进：
不需要将新闻文本数据存储在数据库里，而是只需要存储一个新闻原文链接，这样可以节约资源，而且相对安全，可以同步原文的修改与更新。
实践中遇到的问题及解决方法：
（1）技术框架难以选择。一开始技术选型的时候比较犹豫，不清楚哪些框架是合适的。
（2）需求不够清晰，一开始就想把所有可能的功能都实现，但实际上在短时间内只要实现最主要最核心的功能就好。
（3）刚开始分类模块的模型没有选对，选的是朴素贝叶斯算法，但是它是一种有监督的学习，不适用于对无标签的新闻文本分类，所以之后模型算法的选择需要充分了解其使用场景再使用。
总的来说，此次项目学习了Vue、Springboot、Mybatis-plus等框架的使用，在学习的过程中也遇到了很多困难和问题，但是通过问老师同学，上网搜索资料，查询书籍的方式最终都解决了出现的问题。




参考文献：
【1】	周志华.(2016)机器学习.清华大学出版社

