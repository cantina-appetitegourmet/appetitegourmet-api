DROP TABLE user_responsaveis;
DROP TABLE associacao_usuario;
ALTER TABLE public.contrato DROP CONSTRAINT contrato_responsavel_aluno_id_fkey;
ALTER TABLE public.contrato ADD CONSTRAINT contrato_responsavel_aluno_id_fkey FOREIGN KEY (responsavel_aluno_id) REFERENCES public.responsavel_aluno(id) ON DELETE CASCADE;
ALTER TABLE public.contrato_desconto DROP CONSTRAINT contrato_desconto_contrato_id_fkey;
ALTER TABLE public.contrato_desconto ADD CONSTRAINT contrato_desconto_contrato_id_fkey FOREIGN KEY (contrato_id) REFERENCES public.contrato(id) ON DELETE CASCADE;
ALTER TABLE public.contrato_desconto DROP CONSTRAINT contrato_desconto_desconto_id_fkey;
ALTER TABLE public.contrato_desconto ADD CONSTRAINT contrato_desconto_desconto_id_fkey FOREIGN KEY (desconto_id) REFERENCES public.desconto(id) ON DELETE CASCADE;
ALTER TABLE public.contrato_plano DROP CONSTRAINT contrato_plano_contrato_id_fkey;
ALTER TABLE public.contrato_plano ADD CONSTRAINT contrato_plano_contrato_id_fkey FOREIGN KEY (contrato_id) REFERENCES public.contrato(id) ON DELETE CASCADE;
ALTER TABLE public.contrato_plano DROP CONSTRAINT contrato_plano_planos_alimentares_precos_id_fkey;
ALTER TABLE public.contrato_plano ADD CONSTRAINT contrato_plano_planos_alimentares_precos_id_fkey FOREIGN KEY (planos_alimentares_precos_id) REFERENCES public.planos_alimentares_precos(id) ON DELETE CASCADE;
ALTER TABLE public.pagamento DROP CONSTRAINT pagamento_contrato_id_fkey;
ALTER TABLE public.pagamento ADD CONSTRAINT pagamento_contrato_id_fkey FOREIGN KEY (contrato_id) REFERENCES public.contrato(id) ON DELETE CASCADE;
