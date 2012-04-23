#include <stdlib.h>
#include <stdio.h>

static size_t mem_lens = 0;

long fsize( FILE *fp)
{
    long int save_pos;
    long size_of_file;
	
	if (fp == NULL)
		return 0;
	
    save_pos = ftell( fp );			
	
    fseek( fp, 0L, SEEK_END );		
    size_of_file = ftell( fp );		
    fseek( fp, save_pos, SEEK_SET ); 
	
    return( size_of_file);
}

void* lp_malloc(size_t len)
{
	mem_lens++;
	return malloc(len);
}

void lp_free(void* p)
{
	mem_lens--;
	free(p);
}

void print_mem()
{
	printf("mem = %u\n", mem_lens);
}