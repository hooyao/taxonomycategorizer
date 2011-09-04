package com.gemalto.tools.model;

import java.util.UUID;

public class VoteResponse
{
	private UUID voteId;
	private String[] selectedOps;
	public void setVoteId(UUID voteId)
	{
		this.voteId = voteId;
	}
	public UUID getVoteId()
	{
		return this.voteId;
	}
	public void setSelectedOps(String[] selectedOps)
	{
		this.selectedOps = selectedOps;
	}
	public String[] getSelectedOps()
	{
		return this.selectedOps;
	}
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("VOTE ID:"+this.voteId.toString()+"\n"); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append("Ops:"); //$NON-NLS-1$
		if(this.selectedOps!=null)
		{
			for(int i=0;i<this.selectedOps.length;i++)
			{
				sb.append(this.selectedOps[i]+" "); //$NON-NLS-1$
			}
		}
		sb.append("\n"); //$NON-NLS-1$
		return sb.toString();
	}
	
	
}
