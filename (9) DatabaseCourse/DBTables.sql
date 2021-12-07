-- Table: public.product

-- DROP TABLE public.product;

CREATE TABLE IF NOT EXISTS public.product
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    price numeric NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Product_pkey" PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

ALTER TABLE public.product
    OWNER to postgres;




-- Table: public.product_category

-- DROP TABLE public.product_category;

CREATE TABLE IF NOT EXISTS public.product_category
(
    id_product integer NOT NULL,
    category character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Product_Category_pkey" PRIMARY KEY (id_product),
    CONSTRAINT id_product FOREIGN KEY (id_product)
        REFERENCES public.product (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.product_category
    OWNER to postgres;


data:
product
1,25.5,pizza
14,2.3,eggs
17,3.3,bread
18,10.5,pasta
19,8,rice

product_category
1,italian
14,food
17,food
18,italian
19,italian