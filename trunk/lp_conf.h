#ifndef _LP_CONF_H_
#define _LP_CONF_H_
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

enum{
	LP_FAIL,
	LP_TRUE,
	LP_NIL,
	LP_EXIST,
	LP_END
};


extern size_t mem;
#undef  malloc
#undef free

#define malloc	lp_malloc
#define free	lp_free

typedef FILE*   f_handle;
#define f_open  fopen
#define f_close fclose
#define f_read  fread
#define f_write fwrite  

typedef unsigned char byte;
typedef struct _slice{
	byte* sp;
	byte* b_sp;
	size_t sp_len;
}slice;

#define check_value(e, qe, r)	do{if((e)==(qe)) return (r);}while(0)	
#define check_fail(e, r)		check_value(e, LP_FAIL, r)
#define  check_null(e, r)		check_value(e, NULL, r)	
#define  print printf

void* lp_malloc(size_t len);
void lp_free(void* p);
long fsize( FILE *fp);
void mem_print();

#endif