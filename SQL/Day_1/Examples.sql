-- select *
-- from movies

-- Select title, duration, release_date
-- from movies

-- Select title, imdb_rating, duration
-- from movies
-- where IMDB_RATING > 8.5 and duration > 150

-- select * 
-- from movies
-- order by title desc

-- Select movieid, title as 'Movie Title' , year (release_date) as release_year
-- from movies
-- select * 
-- from movies
-- where year(release_date) > 2005

-- select * 
-- from movies
-- where title LIKE 'the%' 

-- Insert into movies(title)
-- values ('My future cool movie');

-- Select *
-- from movies
-- where release_date  is not null

-- Select *
-- from movies
-- where release_date  is null

-- Select *
-- from movies
-- where RELEASE_DATE >= '2000-01-01' and RELEASE_DATE <= '2010-01-31'

-- select title, release_date, duration
-- from movies
-- where release_Date between '2000-0-01' and '2010-12-31'

-- select title, release_date
-- from movies
-- where month(RELEASE_DATE) = 6 

-- select * 
-- from movies
-- where duration > 140
-- order by IMDB_RATING desc

-- select *, Year(release_date) as 'Year'
-- from movies

-- select *, YEAR(release_date) as 'Year'
-- from movies
-- where RELEASE_DATE is not null
-- order by year (release_date), title

-- select *
-- from movies
-- where (RELEASE_DATE between '1990-01-01' and  '1999-01-01') or 
-- (release_Date between '2000-01-01' and '2010-01-01') 
-- order by RELEASE_DATE asc

-- select *
-- from movies
-- where month(RELEASE_DATE) = 6 
-- or month(release_date) = 7
-- or month(RELEASE_DATE) = 8
-- order by RELEASE_DATE asc

-- select *
-- from movies
-- where month(RELEASE_DATE) in (6,7,8)
-- order by RELEASE_DATE asc 

-- select title
-- from movies
-- where title like 'the%' or title like '%man%'

-- select *
-- from movies
-- where dayname(release_date) in ('Friday', 'Saturday', 'sunday')

select * from movies
where title = 'finding nemo'






