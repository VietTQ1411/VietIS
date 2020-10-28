USE [master]
GO
/****** Object:  Database [VietIS]    Script Date: 10/27/2020 11:20:43 AM ******/
CREATE DATABASE [VietIS]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'VietIS', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\VietIS.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'VietIS_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\VietIS_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [VietIS] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [VietIS].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [VietIS] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [VietIS] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [VietIS] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [VietIS] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [VietIS] SET ARITHABORT OFF 
GO
ALTER DATABASE [VietIS] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [VietIS] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [VietIS] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [VietIS] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [VietIS] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [VietIS] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [VietIS] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [VietIS] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [VietIS] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [VietIS] SET  ENABLE_BROKER 
GO
ALTER DATABASE [VietIS] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [VietIS] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [VietIS] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [VietIS] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [VietIS] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [VietIS] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [VietIS] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [VietIS] SET RECOVERY FULL 
GO
ALTER DATABASE [VietIS] SET  MULTI_USER 
GO
ALTER DATABASE [VietIS] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [VietIS] SET DB_CHAINING OFF 
GO
ALTER DATABASE [VietIS] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [VietIS] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [VietIS] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'VietIS', N'ON'
GO
ALTER DATABASE [VietIS] SET QUERY_STORE = OFF
GO
USE [VietIS]
GO
ALTER DATABASE SCOPED CONFIGURATION SET IDENTITY_CACHE = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET LEGACY_CARDINALITY_ESTIMATION = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET MAXDOP = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET PARAMETER_SNIFFING = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET QUERY_OPTIMIZER_HOTFIXES = PRIMARY;
GO
USE [VietIS]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 10/27/2020 11:20:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [text] NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Food]    Script Date: 10/27/2020 11:20:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Food](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[storeId] [int] NULL,
	[name] [text] NULL,
	[catId] [int] NULL,
	[price] [float] NULL,
	[description] [text] NULL,
	[imageId] [int] NULL,
 CONSTRAINT [PK_Food] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FoodComment]    Script Date: 10/27/2020 11:20:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FoodComment](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[userId] [int] NULL,
	[foodId] [int] NULL,
	[content] [text] NULL,
 CONSTRAINT [PK_FoodComment] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Image]    Script Date: 10/27/2020 11:20:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Image](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[imageURL] [text] NULL,
	[type] [varchar](30) NULL,
 CONSTRAINT [PK_Image] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Notification]    Script Date: 10/27/2020 11:20:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Notification](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [text] NULL,
	[content] [text] NULL,
 CONSTRAINT [PK_Notification] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 10/27/2020 11:20:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[userId] [int] NULL,
	[description] [text] NULL,
	[status] [int] NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 10/27/2020 11:20:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[orderId] [int] NULL,
	[foodId] [int] NULL,
	[quantity] [int] NULL,
	[price] [float] NULL,
 CONSTRAINT [PK_OrderDetail] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhoneToken]    Script Date: 10/27/2020 11:20:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhoneToken](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[tokenKey] [nvarchar](300) NULL,
	[createAt] [date] NULL,
 CONSTRAINT [PK_PhoneToken] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Rating]    Script Date: 10/27/2020 11:20:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Rating](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[foodId] [int] NULL,
	[rating] [float] NULL,
 CONSTRAINT [PK_Rating] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RatingStore]    Script Date: 10/27/2020 11:20:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RatingStore](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[storeId] [int] NULL,
	[rating] [float] NULL,
 CONSTRAINT [PK_RatingStore] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Store]    Script Date: 10/27/2020 11:20:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Store](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [text] NULL,
	[address] [text] NULL,
	[phoneNumber] [varchar](50) NULL,
	[imageId] [int] NULL,
 CONSTRAINT [PK_Store] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[StoreComment]    Script Date: 10/27/2020 11:20:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[StoreComment](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[userId] [int] NULL,
	[storeId] [int] NULL,
	[content] [text] NULL,
 CONSTRAINT [PK_StoreComment] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 10/27/2020 11:20:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[email] [varchar](300) NULL,
	[hashPassword] [varchar](300) NULL,
	[name] [text] NULL,
	[imageId] [int] NULL,
	[phoneNumber] [varchar](50) NULL,
	[address] [text] NULL,
	[userType] [int] NULL,
	[tokenKey] [varchar](300) NULL,
	[expireDate] [date] NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UserToken]    Script Date: 10/27/2020 11:20:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserToken](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[userId] [int] NULL,
	[tokenKey] [text] NULL,
	[createAt] [date] NULL,
 CONSTRAINT [PK_UserToken] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([id], [name]) VALUES (1, N'bún')
INSERT [dbo].[Category] ([id], [name]) VALUES (2, N'com')
INSERT [dbo].[Category] ([id], [name]) VALUES (3, N'd? an v?t')
SET IDENTITY_INSERT [dbo].[Category] OFF
SET IDENTITY_INSERT [dbo].[Food] ON 

INSERT [dbo].[Food] ([id], [storeId], [name], [catId], [price], [description], [imageId]) VALUES (1, 1, N'bun cha', 1, 10000, N'rat ngon', 1)
INSERT [dbo].[Food] ([id], [storeId], [name], [catId], [price], [description], [imageId]) VALUES (2, 2, N'nem lui', 3, 20000, N'ngon', 1)
INSERT [dbo].[Food] ([id], [storeId], [name], [catId], [price], [description], [imageId]) VALUES (3, 1, N'bun dau mam tom', 1, 30000, N'great', 1)
INSERT [dbo].[Food] ([id], [storeId], [name], [catId], [price], [description], [imageId]) VALUES (4, 1, N'bun ca', 1, 12345, N'good', 1)
INSERT [dbo].[Food] ([id], [storeId], [name], [catId], [price], [description], [imageId]) VALUES (5, 1, N'bun ca ch? ', 1, 12345, N'good', 1)
INSERT [dbo].[Food] ([id], [storeId], [name], [catId], [price], [description], [imageId]) VALUES (6, 1, N'bun ca nuong', 2, 12345, N'good', 1)
INSERT [dbo].[Food] ([id], [storeId], [name], [catId], [price], [description], [imageId]) VALUES (7, 1, N'bun ca mam tom', 1, 12345, N'good', 1)
INSERT [dbo].[Food] ([id], [storeId], [name], [catId], [price], [description], [imageId]) VALUES (8, 1, N'bun ca bia om', 1, 12345, N'good', 1)
INSERT [dbo].[Food] ([id], [storeId], [name], [catId], [price], [description], [imageId]) VALUES (9, 1, N'ca nuong', 2, 12345, N'good', 1)
SET IDENTITY_INSERT [dbo].[Food] OFF
SET IDENTITY_INSERT [dbo].[Image] ON 

INSERT [dbo].[Image] ([id], [imageURL], [type]) VALUES (1, N'default', N'')
SET IDENTITY_INSERT [dbo].[Image] OFF
SET IDENTITY_INSERT [dbo].[PhoneToken] ON 

INSERT [dbo].[PhoneToken] ([id], [tokenKey], [createAt]) VALUES (1, N'dsadasd', NULL)
INSERT [dbo].[PhoneToken] ([id], [tokenKey], [createAt]) VALUES (2, N'bv', NULL)
SET IDENTITY_INSERT [dbo].[PhoneToken] OFF
SET IDENTITY_INSERT [dbo].[RatingStore] ON 

INSERT [dbo].[RatingStore] ([id], [storeId], [rating]) VALUES (1, 1, 4)
INSERT [dbo].[RatingStore] ([id], [storeId], [rating]) VALUES (2, 2, 4)
INSERT [dbo].[RatingStore] ([id], [storeId], [rating]) VALUES (3, 1, 3)
INSERT [dbo].[RatingStore] ([id], [storeId], [rating]) VALUES (4, 2, 5)
SET IDENTITY_INSERT [dbo].[RatingStore] OFF
SET IDENTITY_INSERT [dbo].[Store] ON 

INSERT [dbo].[Store] ([id], [name], [address], [phoneNumber], [imageId]) VALUES (1, N'nha làm', N'ha noi', N'1234', 1)
INSERT [dbo].[Store] ([id], [name], [address], [phoneNumber], [imageId]) VALUES (2, N'b?i', N'da nang', N'4321', 1)
SET IDENTITY_INSERT [dbo].[Store] OFF
SET IDENTITY_INSERT [dbo].[User] ON 

INSERT [dbo].[User] ([id], [email], [hashPassword], [name], [imageId], [phoneNumber], [address], [userType], [tokenKey], [expireDate]) VALUES (33, N'vettq@gmail.com', N'$2b$10$S9k17cwBioUHZ0mkGoYuQ.H6RAJPIV9a8XsWiA4RmimlzEFw9c57y', N'vettq', 1, N'123456789', N'hung yên', 1, N'^1O=r5?/J=Zchc7w', CAST(N'2020-11-20' AS Date))
INSERT [dbo].[User] ([id], [email], [hashPassword], [name], [imageId], [phoneNumber], [address], [userType], [tokenKey], [expireDate]) VALUES (37, N'viettq@gmail.com', N'$2b$10$DjXzcJlimC2iwTt1/g.zpOZ1M7js0QIvjufCFGvROsR.5p/oEUp2y', N'vettq', NULL, NULL, NULL, 1, N'J4iY6ICp8Fn<-Fip', CAST(N'2020-11-20' AS Date))
INSERT [dbo].[User] ([id], [email], [hashPassword], [name], [imageId], [phoneNumber], [address], [userType], [tokenKey], [expireDate]) VALUES (44, N'viettq2@gmail.com', N'$2b$10$u4BUTmZEJiL6bCYgpR8y9.ww0hLxop98SPAzROnheD1x3w6T.13P2', N'vettq', 1, NULL, NULL, 1, N'4N72\=y7s#:^59G_', CAST(N'2020-11-20' AS Date))
INSERT [dbo].[User] ([id], [email], [hashPassword], [name], [imageId], [phoneNumber], [address], [userType], [tokenKey], [expireDate]) VALUES (45, N'vit@gmail.com', N'$2b$10$lzzOswa78WYeDvH9qNG1iuA1hMu7/nmqYB6KJ4crJy1kr7./KjiDK', N'vit@gmail.com', 1, NULL, NULL, 1, N'E<"^%G>\GaIV01?d', CAST(N'2020-11-25' AS Date))
INSERT [dbo].[User] ([id], [email], [hashPassword], [name], [imageId], [phoneNumber], [address], [userType], [tokenKey], [expireDate]) VALUES (46, N'viett2q2@gmail.com', N'$2b$10$icHLKeFb1ye4QyQ/CzWHmOTKiKlaT2oYtkcAYvwWj0lrNH27SVsZi', N'vettq', 1, NULL, NULL, 1, N'm8`q9v(`eS1uR.=|', CAST(N'2020-11-20' AS Date))
INSERT [dbo].[User] ([id], [email], [hashPassword], [name], [imageId], [phoneNumber], [address], [userType], [tokenKey], [expireDate]) VALUES (47, N'viett2q3@gmail.com', N'$2b$10$5k4.Po9JoQxd.tFgxWTwL.RqWHYHpM6xjtaIkIPUx.K0kbl17KLyO', N'vettq', 1, NULL, NULL, 1, N'93Xl>&6>owt5&idk', CAST(N'2020-11-20' AS Date))
INSERT [dbo].[User] ([id], [email], [hashPassword], [name], [imageId], [phoneNumber], [address], [userType], [tokenKey], [expireDate]) VALUES (48, N'viet12345@gmail.com', N'$2b$10$waWdpxP9DTnNtljsaK1Kv.EJnq9IFK43NkllgBP0VQzsa2YDJBjPG', N'vietiiiii', 1, NULL, NULL, 1, N's''74Od>WV54f\1x[', CAST(N'2020-11-20' AS Date))
INSERT [dbo].[User] ([id], [email], [hashPassword], [name], [imageId], [phoneNumber], [address], [userType], [tokenKey], [expireDate]) VALUES (49, N'asd@gmail.com', N'$2b$10$RU8iMRH2X72.gselrdeVxOgS0cZ3JSSpaeZ3Dau0gQUFTMdCys/0i', N'asdasd', 1, NULL, NULL, 1, N'n!e^3uvzYa8''''"85', CAST(N'2020-11-20' AS Date))
INSERT [dbo].[User] ([id], [email], [hashPassword], [name], [imageId], [phoneNumber], [address], [userType], [tokenKey], [expireDate]) VALUES (50, N'qasd@gmail.com', N'$2b$10$KOlfIbzSRKoTl3Z9yDLU5uuqCDs3rHGb/mmAB7asHIRmeY/4epvHG', N'asdzxc', 1, NULL, NULL, 1, N'+"E9RC[".*D;&Vo)', CAST(N'2020-11-20' AS Date))
INSERT [dbo].[User] ([id], [email], [hashPassword], [name], [imageId], [phoneNumber], [address], [userType], [tokenKey], [expireDate]) VALUES (51, N'asdsad@gmail.com', N'$2b$10$5Sk04dMJ.GTcAzT82RMaMORq7dyf.Wiv0/6vzYFjZPB7.DcdK37W2', N'hsudfh', 1, NULL, NULL, 1, N'g;0)pS5?8-kQU#cF', CAST(N'2020-11-20' AS Date))
INSERT [dbo].[User] ([id], [email], [hashPassword], [name], [imageId], [phoneNumber], [address], [userType], [tokenKey], [expireDate]) VALUES (52, N'asdasd@gmail.com', N'$2b$10$yLyM/wdeCYA5FSy9ZkbC.OFnOxJNvNxXkEFlTMy9aHcWlHhcYTv/K', N'asdkasjd', 1, NULL, NULL, 1, N'ITqsiNW95[\O(.ie', CAST(N'2020-11-20' AS Date))
SET IDENTITY_INSERT [dbo].[User] OFF
SET ANSI_PADDING ON
GO
/****** Object:  Index [UC_User]    Script Date: 10/27/2020 11:20:45 AM ******/
ALTER TABLE [dbo].[User] ADD  CONSTRAINT [UC_User] UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[User] ADD  CONSTRAINT [DF_User_imageId]  DEFAULT ((1)) FOR [imageId]
GO
ALTER TABLE [dbo].[UserToken] ADD  CONSTRAINT [DF_UserToken_createAt]  DEFAULT (getdate()) FOR [createAt]
GO
ALTER TABLE [dbo].[Food]  WITH CHECK ADD  CONSTRAINT [FK_Food_Category] FOREIGN KEY([catId])
REFERENCES [dbo].[Category] ([id])
GO
ALTER TABLE [dbo].[Food] CHECK CONSTRAINT [FK_Food_Category]
GO
ALTER TABLE [dbo].[Food]  WITH CHECK ADD  CONSTRAINT [FK_Food_Image] FOREIGN KEY([imageId])
REFERENCES [dbo].[Image] ([id])
GO
ALTER TABLE [dbo].[Food] CHECK CONSTRAINT [FK_Food_Image]
GO
ALTER TABLE [dbo].[Food]  WITH CHECK ADD  CONSTRAINT [FK_Food_Store] FOREIGN KEY([storeId])
REFERENCES [dbo].[Store] ([id])
GO
ALTER TABLE [dbo].[Food] CHECK CONSTRAINT [FK_Food_Store]
GO
ALTER TABLE [dbo].[FoodComment]  WITH CHECK ADD  CONSTRAINT [FK_FoodComment_Food] FOREIGN KEY([foodId])
REFERENCES [dbo].[Food] ([id])
GO
ALTER TABLE [dbo].[FoodComment] CHECK CONSTRAINT [FK_FoodComment_Food]
GO
ALTER TABLE [dbo].[FoodComment]  WITH CHECK ADD  CONSTRAINT [FK_FoodComment_User] FOREIGN KEY([userId])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[FoodComment] CHECK CONSTRAINT [FK_FoodComment_User]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_User] FOREIGN KEY([userId])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_User]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Food] FOREIGN KEY([foodId])
REFERENCES [dbo].[Food] ([id])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Food]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Order] FOREIGN KEY([orderId])
REFERENCES [dbo].[Order] ([id])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Order]
GO
ALTER TABLE [dbo].[Rating]  WITH CHECK ADD  CONSTRAINT [FK_Rating_Food] FOREIGN KEY([foodId])
REFERENCES [dbo].[Food] ([id])
GO
ALTER TABLE [dbo].[Rating] CHECK CONSTRAINT [FK_Rating_Food]
GO
ALTER TABLE [dbo].[RatingStore]  WITH CHECK ADD  CONSTRAINT [FK_RatingStore_Store] FOREIGN KEY([storeId])
REFERENCES [dbo].[Store] ([id])
GO
ALTER TABLE [dbo].[RatingStore] CHECK CONSTRAINT [FK_RatingStore_Store]
GO
ALTER TABLE [dbo].[Store]  WITH CHECK ADD  CONSTRAINT [FK_Store_Image] FOREIGN KEY([imageId])
REFERENCES [dbo].[Image] ([id])
GO
ALTER TABLE [dbo].[Store] CHECK CONSTRAINT [FK_Store_Image]
GO
ALTER TABLE [dbo].[StoreComment]  WITH CHECK ADD  CONSTRAINT [FK_StoreComment_Store] FOREIGN KEY([storeId])
REFERENCES [dbo].[Store] ([id])
GO
ALTER TABLE [dbo].[StoreComment] CHECK CONSTRAINT [FK_StoreComment_Store]
GO
ALTER TABLE [dbo].[StoreComment]  WITH CHECK ADD  CONSTRAINT [FK_StoreComment_User] FOREIGN KEY([userId])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[StoreComment] CHECK CONSTRAINT [FK_StoreComment_User]
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [FK_User_Image] FOREIGN KEY([imageId])
REFERENCES [dbo].[Image] ([id])
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [FK_User_Image]
GO
ALTER TABLE [dbo].[UserToken]  WITH CHECK ADD  CONSTRAINT [FK_UserToken_User] FOREIGN KEY([userId])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[UserToken] CHECK CONSTRAINT [FK_UserToken_User]
GO
USE [master]
GO
ALTER DATABASE [VietIS] SET  READ_WRITE 
GO
